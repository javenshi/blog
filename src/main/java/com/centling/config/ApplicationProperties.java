package com.centling.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;


/**
 * Properties specific to JHipster.
 *
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Async async = new Async();

    private final Http http = new Http();

    private final Cache cache = new Cache();

    private final Mail mail = new Mail();

    private final Security security = new Security();

    private final Swagger swagger = new Swagger();

    private final Metrics metrics = new Metrics();

    private final CorsConfiguration cors = new CorsConfiguration();
    private final ThriftServer thriftServer = new ThriftServer();
    private final ThriftClientConnectionPool thriftClientConnectionPool = new ThriftClientConnectionPool();

    private final Submail submail = new Submail();

    private final Redis redis = new Redis();

    private final SealToken sealToken = new SealToken();
    private final SmartSealBizClientConnectionPool smartSealBizClientConnectionPool = new SmartSealBizClientConnectionPool();

    public static class SealToken{
        private final SmartSealBiz smartSealBiz = new SmartSealBiz();
        public SmartSealBiz getSmartSealBiz() {
            return smartSealBiz;
        }
    }
    public static class SmartSealBiz {
        private String key;
        private String token;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
    /**
	 * @return the thriftClientConnectionPool
	 */
	public ThriftClientConnectionPool getThriftClientConnectionPool() {
		
		return thriftClientConnectionPool;
	}



	/**
	 * @return the thriftServer
	 */
	public ThriftServer getThriftServer() {
		
		return thriftServer;
	}

	public Async getAsync() {
        return async;
    }

    public Http getHttp() {
        return http;
    }

    public Cache getCache() {
        return cache;
    }

    public Mail getMail() {
        return mail;
    }

    public Security getSecurity() {
        return security;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public static class Async {

        private int corePoolSize = 2;

        private int maxPoolSize = 50;

        private int queueCapacity = 10000;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

    public static class Http {

        private final Cache cache = new Cache();

        public Cache getCache() {
            return cache;
        }

        public static class Cache {

            private int timeToLiveInDays = 1461;

            public int getTimeToLiveInDays() {
                return timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays) {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }
    }

    public static class Cache {

        private int timeToLiveSeconds = 3600;

        private final Ehcache ehcache = new Ehcache();

        public int getTimeToLiveSeconds() {
            return timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(int timeToLiveSeconds) {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public Ehcache getEhcache() {
            return ehcache;
        }

        public static class Ehcache {

            private String maxBytesLocalHeap = "16M";

            public String getMaxBytesLocalHeap() {
                return maxBytesLocalHeap;
            }

            public void setMaxBytesLocalHeap(String maxBytesLocalHeap) {
                this.maxBytesLocalHeap = maxBytesLocalHeap;
            }
        }
    }

    public static class Mail {

        private String from = "yz@localhost";

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }
    }

    public static class Security {

        private final Authentication authentication = new Authentication();

        public Authentication getAuthentication() {
            return authentication;
        }

        public static class Authentication {

            private final Jwt jwt = new Jwt();

            public Jwt getJwt() {
                return jwt;
            }

            public static class Jwt {

                private String secret;

                private long tokenValidityInSeconds = 60*60;
                private long tokenValidityInSecondsForRememberMe = 2592000;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getTokenValidityInSecondsForRememberMe() {
                    return tokenValidityInSecondsForRememberMe;
                }

                public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                }
            }
        }
    }

    public static class Swagger {

        private String title = "yz API";

        private String description = "yz API documentation";

        private String version = "0.0.1";

        private String termsOfServiceUrl;

        private String contactName;

        private String contactUrl;

        private String contactEmail;

        private String license;

        private String licenseUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }
    }

    public static class Metrics {

        private final Jmx jmx = new Jmx();

        private final Spark spark = new Spark();

        private final Graphite graphite = new Graphite();

        private final Logs logs = new Logs();

        public Jmx getJmx() {
            return jmx;
        }

        public Spark getSpark() {
            return spark;
        }

        public Graphite getGraphite() {
            return graphite;
        }

        public Logs getLogs() {
            return logs;
        }


        public static class Jmx {

            private boolean enabled = true;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

        public static class Spark {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 9999;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }
        }

        public static class Graphite {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 2003;

            private String prefix = "yz";

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }

        public static  class Logs {

            private boolean enabled = false;

            private long reportFrequency = 60;

            public long getReportFrequency() {
                return reportFrequency;
            }

            public void setReportFrequency(int reportFrequency) {
                this.reportFrequency = reportFrequency;
            }

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }

    private final Logging logging = new Logging();

    public Logging getLogging() { return logging; }

    public static class Logging {

        private final Logstash logstash = new Logstash();

        public Logstash getLogstash() { return logstash; }

        public static class Logstash {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 5000;

            private int queueSize = 512;

            public boolean isEnabled() { return enabled; }

            public void setEnabled(boolean enabled) { this.enabled = enabled; }

            public String getHost() { return host; }

            public void setHost(String host) { this.host = host; }

            public int getPort() { return port; }

            public void setPort(int port) { this.port = port; }

            public int getQueueSize() { return queueSize; }

            public void setQueueSize(int queueSize) { this.queueSize = queueSize; }
        }
    }
    public static class ThriftServer{
    	/**
    	 * 默认本地thrift接口8888,yml文件中配置可覆盖本参数，
    	 */
    	private int port = 8888;
		
		/**
		 * @return the port
		 */
		public int getPort() {
			
			return port;
		}

		
		/**
		 * @param port the port to set
		 */
		public void setPort(int port) {
			
			this.port = port;
		}
    	
    	
    }
    
    public static class ThriftClientConnectionPool{
    	private String serviceIP = "219.146.90.234";
    	private int servicePort = 9310;
    	
//        private String serviceIP = "127.0.0.1";
//    	private int servicePort = 8888;
    	private int maxActive = 10;
    	private int maxIdle = 10;
    	private boolean testOnBorrow = true;
    	private boolean testOnReturn = true;
    	private boolean testWhileIdle = true;
    	private int conTimeOut = 2000;
		
		/**
		 * @return the serviceIP
		 */
		public String getServiceIP() {
			
			return serviceIP;
		}
		
		/**
		 * @param serviceIP the serviceIP to set
		 */
		public void setServiceIP(String serviceIP) {
			
			this.serviceIP = serviceIP;
		}
		
		/**
		 * @return the servicePort
		 */
		public int getServicePort() {
			
			return servicePort;
		}
		
		/**
		 * @param servicePort the servicePort to set
		 */
		public void setServicePort(int servicePort) {
			
			this.servicePort = servicePort;
		}
		
		/**
		 * @return the maxActive
		 */
		public int getMaxActive() {
			
			return maxActive;
		}
		
		/**
		 * @param maxActive the maxActive to set
		 */
		public void setMaxActive(int maxActive) {
			
			this.maxActive = maxActive;
		}
		
		/**
		 * @return the maxIdle
		 */
		public int getMaxIdle() {
			
			return maxIdle;
		}
		
		/**
		 * @param maxIdle the maxIdle to set
		 */
		public void setMaxIdle(int maxIdle) {
			
			this.maxIdle = maxIdle;
		}
		
		/**
		 * @return the testOnBorrow
		 */
		public boolean isTestOnBorrow() {
			
			return testOnBorrow;
		}
		
		/**
		 * @param testOnBorrow the testOnBorrow to set
		 */
		public void setTestOnBorrow(boolean testOnBorrow) {
			
			this.testOnBorrow = testOnBorrow;
		}
		
		/**
		 * @return the testOnReturn
		 */
		public boolean isTestOnReturn() {
			
			return testOnReturn;
		}
		
		/**
		 * @param testOnReturn the testOnReturn to set
		 */
		public void setTestOnReturn(boolean testOnReturn) {
			
			this.testOnReturn = testOnReturn;
		}
		
		/**
		 * @return the testWhileIdle
		 */
		public boolean isTestWhileIdle() {
			
			return testWhileIdle;
		}
		
		/**
		 * @param testWhileIdle the testWhileIdle to set
		 */
		public void setTestWhileIdle(boolean testWhileIdle) {
			
			this.testWhileIdle = testWhileIdle;
		}
		
		/**
		 * @return the conTimeOut
		 */
		public int getConTimeOut() {
			
			return conTimeOut;
		}
		
		/**
		 * @param conTimeOut the conTimeOut to set
		 */
		public void setConTimeOut(int conTimeOut) {
			
			this.conTimeOut = conTimeOut;
		}
    	
    	
    	
    }
    public static class SmartSealBizClientConnectionPool{
    	private String serviceIP = "127.0.0.1";
    	private int servicePort = 8888;
        private String appKey;
        private String appToke;

//        private String serviceIP = "127.0.0.1";
//    	private int servicePort = 8888;
    	private int maxActive = 100;
    	private int maxIdle = 100;
    	private boolean testOnBorrow = true;
    	private boolean testOnReturn = true;
    	private boolean testWhileIdle = true;
    	private int conTimeOut = 200000;

		/**
		 * @return the serviceIP
		 */
		public String getServiceIP() {

			return serviceIP;
		}

		/**
		 * @param serviceIP the serviceIP to set
		 */
		public void setServiceIP(String serviceIP) {

			this.serviceIP = serviceIP;
		}

		/**
		 * @return the servicePort
		 */
		public int getServicePort() {

			return servicePort;
		}

		/**
		 * @param servicePort the servicePort to set
		 */
		public void setServicePort(int servicePort) {

			this.servicePort = servicePort;
		}

		/**
		 * @return the maxActive
		 */
		public int getMaxActive() {

			return maxActive;
		}

		/**
		 * @param maxActive the maxActive to set
		 */
		public void setMaxActive(int maxActive) {

			this.maxActive = maxActive;
		}

		/**
		 * @return the maxIdle
		 */
		public int getMaxIdle() {

			return maxIdle;
		}

		/**
		 * @param maxIdle the maxIdle to set
		 */
		public void setMaxIdle(int maxIdle) {

			this.maxIdle = maxIdle;
		}

		/**
		 * @return the testOnBorrow
		 */
		public boolean isTestOnBorrow() {

			return testOnBorrow;
		}

		/**
		 * @param testOnBorrow the testOnBorrow to set
		 */
		public void setTestOnBorrow(boolean testOnBorrow) {

			this.testOnBorrow = testOnBorrow;
		}

		/**
		 * @return the testOnReturn
		 */
		public boolean isTestOnReturn() {

			return testOnReturn;
		}

		/**
		 * @param testOnReturn the testOnReturn to set
		 */
		public void setTestOnReturn(boolean testOnReturn) {

			this.testOnReturn = testOnReturn;
		}

		/**
		 * @return the testWhileIdle
		 */
		public boolean isTestWhileIdle() {

			return testWhileIdle;
		}

		/**
		 * @param testWhileIdle the testWhileIdle to set
		 */
		public void setTestWhileIdle(boolean testWhileIdle) {

			this.testWhileIdle = testWhileIdle;
		}

		/**
		 * @return the conTimeOut
		 */
		public int getConTimeOut() {

			return conTimeOut;
		}

		/**
		 * @param conTimeOut the conTimeOut to set
		 */
		public void setConTimeOut(int conTimeOut) {

			this.conTimeOut = conTimeOut;
		}

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getAppToke() {
            return appToke;
        }

        public void setAppToke(String appToke) {
            this.appToke = appToke;
        }
    }

    public SealToken getSealToken() {
        return sealToken;
    }

    public SmartSealBizClientConnectionPool getSmartSealBizClientConnectionPool() {
        return smartSealBizClientConnectionPool;
    }


    public static class Submail{

        private int msgSettingStatus;
        private String appId;
        private String appKey;
        private String keyWord;


        public int getMsgSettingStatus() {
            return msgSettingStatus;
        }

        public void setMsgSettingStatus(int msgSettingStatus) {
            this.msgSettingStatus = msgSettingStatus;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }
    }

    public Submail getSubmail() {
        return submail;
    }


    public static class Redis{
        private String host;
        private int port;
        private int database =0;
        private int timeout =-1;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getDatabase() {
            return database;
        }

        public void setDatabase(int database) {
            this.database = database;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }
    }

    public Redis getRedis() {
        return redis;
    }
}
