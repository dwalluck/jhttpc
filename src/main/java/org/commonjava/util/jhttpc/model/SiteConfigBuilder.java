/**
 * Copyright (C) 2015 Red Hat, Inc. (jdcasey@commonjava.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.util.jhttpc.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.commonjava.util.jhttpc.model.SiteConfig.DEFAULT_CONNECTION_POOL_TIMEOUT_SECONDS;
import static org.commonjava.util.jhttpc.model.SiteConfig.DEFAULT_MAX_CONNECTIONS;
import static org.commonjava.util.jhttpc.model.SiteConfig.DEFAULT_PROXY_PORT;
import static org.commonjava.util.jhttpc.model.SiteConfig.DEFAULT_REQUEST_TIMEOUT_SECONDS;

/**
 * Created by jdcasey on 10/28/15.
 */
public class SiteConfigBuilder
{

    private String id;

    private String uri;

    private String user;

    private String proxyHost;

    private Integer proxyPort;

    private String proxyUser;

    private SiteTrustType trustType;

    private String keyCertPem;

    private String serverCertPem;

    private Map<String, Object> attributes;

    private Integer requestTimeoutSeconds;

    private Integer connectionPoolTimeoutSeconds;

    private Integer maxConnections;

    public Map<String, Object> getAttributes()
    {
        return attributes;
    }

    public SiteConfigBuilder()
    {
    }

    public SiteConfigBuilder( String id, String uri )
    {
        this.id = id;
        this.uri = uri;
    }

    public SiteConfig build()
    {
        return new SiteConfig( id, uri, user, proxyHost, proxyPort, proxyUser, trustType, keyCertPem, serverCertPem,
                               requestTimeoutSeconds, connectionPoolTimeoutSeconds, maxConnections, attributes );
    }

    public String getId()
    {
        return id;
    }

    public String getProxyHost()
    {
        return proxyHost;
    }

    public int getProxyPort()
    {
        return proxyPort == null ? DEFAULT_PROXY_PORT : proxyPort;
    }

    public String getUser()
    {
        return user;
    }

    public String getHost()
            throws MalformedURLException
    {
        return new URL( getUri() ).getHost();
    }

    public int getPort()
            throws MalformedURLException
    {
        URL u = new URL( getUri() );
        int port = u.getPort();
        if ( port < 1 )
        {
            port = u.getDefaultPort();
        }

        return port;
    }

    public String getProxyUser()
    {
        return proxyUser;
    }

    public String getKeyCertPem()
    {
        return keyCertPem;
    }

    public String getUri()
    {
        return uri;
    }

    public String getServerCertPem()
    {
        return serverCertPem;
    }

    public int getRequestTimeoutSeconds()
    {
        return requestTimeoutSeconds == null ? DEFAULT_REQUEST_TIMEOUT_SECONDS : requestTimeoutSeconds;
    }

    public int getConnectionPoolTimeoutSeconds()
    {
        return connectionPoolTimeoutSeconds == null ?
                DEFAULT_CONNECTION_POOL_TIMEOUT_SECONDS :
                connectionPoolTimeoutSeconds;
    }

    public synchronized Object setAttribute( String key, Object value )
    {
        if ( attributes == null )
        {
            attributes = new HashMap<String, Object>();
        }

        return attributes.put( key, value );
    }

    public Object getAttribute( String key )
    {
        return attributes == null ? null : attributes.get( key );
    }

    public SiteConfigBuilder withId( String id )
    {
        this.id = id;
        return this;
    }

    public SiteConfigBuilder withUri( String uri )
    {
        this.uri = uri;
        return this;
    }

    public SiteConfigBuilder withUser( String user )
    {
        this.user = user;
        return this;
    }

    public SiteConfigBuilder withProxyHost( String proxyHost )
    {
        this.proxyHost = proxyHost;
        return this;
    }

    public SiteConfigBuilder withProxyPort( Integer proxyPort )
    {
        this.proxyPort = proxyPort;
        return this;
    }

    public SiteConfigBuilder withProxyUser( String proxyUser )
    {
        this.proxyUser = proxyUser;
        return this;
    }

    public SiteConfigBuilder withTrustType( SiteTrustType trustType )
    {
        this.trustType = trustType;
        return this;
    }

    public SiteConfigBuilder withKeyCertPem( String keyCertPem )
    {
        this.keyCertPem = keyCertPem;
        return this;
    }

    public SiteConfigBuilder withServerCertPem( String serverCertPem )
    {
        this.serverCertPem = serverCertPem;
        return this;
    }

    public SiteConfigBuilder withAttributes( Map<String, Object> attributes )
    {
        this.attributes = attributes;
        return this;
    }

    public SiteConfigBuilder withRequestTimeoutSeconds( Integer requestTimeoutSeconds )
    {
        this.requestTimeoutSeconds = requestTimeoutSeconds;
        return this;
    }

    public SiteConfigBuilder withConnectionPoolTimeoutSeconds( Integer timeoutSeconds )
    {
        this.connectionPoolTimeoutSeconds = timeoutSeconds;
        return this;
    }

    public int getMaxConnections()
    {
        return maxConnections == null ? DEFAULT_MAX_CONNECTIONS : maxConnections;
    }

    public SiteConfigBuilder withMaxConnections( Integer maxConnections )
    {
        this.maxConnections = maxConnections;
        return this;
    }

    public SiteTrustType getTrustType()
    {
        return trustType == null ? SiteTrustType.DEFAULT : trustType;
    }

    public void removeAttribute( String key )
    {
        if ( attributes != null )
        {
            attributes.remove( key );
        }
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( !( o instanceof SiteConfigBuilder ) )
        {
            return false;
        }

        SiteConfigBuilder that = (SiteConfigBuilder) o;

        return !( getId() != null ? !getId().equals( that.getId() ) : that.getId() != null );

    }

    @Override
    public int hashCode()
    {
        return 13 + ( getId() != null ? getId().hashCode() : 0 );
    }

    @Override
    public String toString()
    {
        return "SiteConfig{" +
                "id='" + id + '\'' +
                '}';
    }
}
