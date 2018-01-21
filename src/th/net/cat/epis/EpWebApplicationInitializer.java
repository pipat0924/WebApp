package th.net.cat.epis;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
//import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
//import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.sql.DataSource;

import org.reflections.Reflections;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jcraft.jsch.ChannelSftp;

import net.sf.jett.transform.ExcelTransformer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.entity.Officer;
import th.net.cat.epis.repo.OfficerRepository;

public class EpWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
//		ServletRegistration.Dynamic cxfServlet = servletContext.addServlet("repositoryRestDispatcher", RepositoryRestDispatcherServlet.class);
//		cxfServlet.setLoadOnStartup(1);
//		cxfServlet.addMapping("/service/*");
	}
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				RootContextConfig.class
				,SecurityConfig.class
				,EpisRepositoryConfiguration.class
				,ViewCrmRepositoryConfiguration.class
				,BillingRepositoryConfiguration.class
				,ContextBatch.class
				,ContextIntegration.class };
	}
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfiguration.class };
	}
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	protected Filter[] getServletFilters() {
		 CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { new EpContextHolder.Filter() ,characterEncodingFilter};
	}

	static final String AUTHORITY_ADMIN = "ADMIN";
	static final String AUTHORITY_SUP = "Suppervisor";
	static final String AUTHORITY_AGENT = "AGENT";
	static final String AUTHORITY_BASIC = "BASIC";
	static final String AUTHORITY_GFMISAGENT = "GFMISAGENT";
	static final String PAGE_LOGIN = "/pages/login.jsp";
//	static final String PAGE_MAIN_ADMIN = "/admin/main.jsp";
	static final String PAGE_MAIN_AGENT = "/pages/main.jsp";
	static final String PAGE_HOME_CONTROLLER ="/home";
	static final String PAGE_ERROR = "/pages/error500.jsp";
	static final String AUTHORITY_TRANSFER = "TRANSFER";
	static final String AUTHORITY_TRANSIN = "TRANSIN";
	/* JavaScript: For checking, if session is expired in frame.
	if (window.self !== window.top) {
		window.top.location = "login.jsp?session_expired";
	}
	*/
	public static class CharsetFilter implements javax.servlet.Filter {
		private String encoding = "UTF-8";
		@Override
		public void init(FilterConfig config) throws ServletException {}
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
				throws IOException, ServletException {
			System.out.println("CharsetFilter");
			// Respect the client-specified character encoding
			// (see HTTP specification section 3.4.1)
			if (null == request.getCharacterEncoding()) {
				request.setCharacterEncoding(encoding);
			}
			// Set the default response content type and encoding
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			next.doFilter(request, response);
		}
		@Override
		public void destroy() {}
	}

	public static class CheckRequestUriFilter implements javax.servlet.Filter {
		@Override public void init(FilterConfig fc) throws ServletException {}
		@Override public void destroy() {}
		@Override
		public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain fc)  {
			System.out.println("CheckRequestUriFilter");
			final HttpInfo httpInfo = new HttpInfo();
			HttpServletRequest req = new HttpServletRequestWrapper((HttpServletRequest) rq);
			HttpServletResponse res = new HttpServletResponseWrapper((HttpServletResponse) rs) {
				@Override public void sendError(int sc) throws IOException { httpInfo.errorCode = sc; }
				@Override public void sendError(int sc, String msg) throws IOException { httpInfo.errorCode = sc; }
			};
			String rqUri = req.getRequestURI();
			String ctxPt = req.getContextPath();
			if (rqUri.equals(ctxPt)
					|| rqUri.equals(new StringBuilder(ctxPt).append("/").toString())
					|| rqUri.equals(new StringBuilder(ctxPt).append("/index.html").toString())
					|| rqUri.endsWith("/")) {
				try {
					res.sendRedirect(new StringBuilder(ctxPt).append(PAGE_LOGIN).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			try {
				System.out.println("RequestURI: "+ rqUri);
				System.out.println("Accept: "+ req.getHeader("accept"));
				fc.doFilter(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (httpInfo.errorCode != 0) {
					try {
						res.sendRedirect(new StringBuilder(ctxPt).append(PAGE_ERROR).toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	public static abstract class ContextHolderFilter<T> implements javax.servlet.Filter {
		protected static ContextHolderFilter<?> FILTER;
		protected FilterConfig filterConfig;
		protected JdbcTemplate jdbcTemplate;
		@Override public void destroy() {}
		@Override public void init(FilterConfig filterConfig) throws ServletException { this.filterConfig = filterConfig; }
		@SuppressWarnings("unchecked")
		@Override
		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
			System.out.println("ContextHolderFilter");
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			String user = SecurityContextHolder.getContext().getAuthentication() == null ? null : SecurityContextHolder.getContext().getAuthentication().getName();
			Object context = request.getSession().getAttribute("epContext");
			if (context == null && isNotBlank(user) && !"anonymousUser".equals(user)) {
				request.getSession().setAttribute("epContext", context = newContext());
			}
			setContext((T) context);
			filterChain.doFilter(servletRequest, servletResponse);
		}
		public static javax.servlet.Filter init(JdbcTemplate jdbcTemplate) { FILTER.jdbcTemplate = jdbcTemplate; return FILTER; }
		public abstract void setContext(T t);
		public abstract T newContext();
	}
	public static class CheckLoggedInFilter implements javax.servlet.Filter {
		@Override public void init(FilterConfig paramFilterConfig) throws ServletException {}
		@Override public void destroy() {}
		@Override
		public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain fc)  {
			System.out.println("CheckLoggedInFilter");
			HttpServletRequest req = new HttpServletRequestWrapper((HttpServletRequest) rq);
			HttpServletResponse res = new HttpServletResponseWrapper((HttpServletResponse) rs);
			String rqUri = req.getRequestURI();
			String ctxPt = req.getContextPath();
			//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 if (rqUri.equals(new StringBuilder(ctxPt).append(PAGE_LOGIN).toString())
					&& authentication != null
					&& authentication.isAuthenticated()
					&& authentication instanceof UsernamePasswordAuthenticationToken) {

				WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(rq.getServletContext());
				OfficerRepository officerRepository = (OfficerRepository) wac.getBean(OfficerRepository.class);
				//Officer officer = officerRepository.findByName((String)authentication.getPrincipal());
				//Officer officer = officerRepository.findByName((String)authentication.getPrincipal());//comment by kao 25600707
				//start add by kao 25600707
				Officer officer = officerRepository.findByName(authentication.getName());
				//end add by kao 25600707
//				if(officer != null && !"Y".equalsIgnoreCase(officer.getVerifyFlag())){
				String grantedUserAuthority = null;
				Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				for(GrantedAuthority grantedAuthority : authorities) {
					grantedUserAuthority = grantedAuthority.getAuthority();
				}
				

				try {
					//res.sendRedirect(new StringBuilder(ctxPt).append(AUTHORITY_ADMIN.equals(grantedUserAuthority)?PAGE_MAIN_ADMIN:PAGE_MAIN_AGENT).toString()); //comment by kao 25600915 0951
					String contextPath = req.getContextPath();
					res.sendRedirect(new StringBuilder(ctxPt).append(PAGE_HOME_CONTROLLER).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
//				}else{
////					Map<String, String[]>  paramMap = new TreeMap<String, String[]>();	//rq.getParameterMap();
////					paramMap.put("username", new String[]{(String)authentication.getPrincipal()});
////					paramMap.put("password", new String[]{""});
////					HttpServletRequest rqWrapper = new EpRequestWrapper((HttpServletRequest)rq, paramMap);
////					rq.getRequestDispatcher(PAGE_LOGIN).forward(rqWrapper, rs);
////					return;
//				}
			}
			try {
				fc.doFilter(rq, rs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static class CheckSSOLoggedInFilter implements javax.servlet.Filter {
	
		@Override public void init(FilterConfig paramFilterConfig) throws ServletException {}
		@Override public void destroy() {}
		@Override
		public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain fc) throws IOException, ServletException {
			System.out.println("CheckSSOLoggedInFilter");
			HttpServletRequest req = new HttpServletRequestWrapper((HttpServletRequest) rq);
			String application = (null == rq.getParameter("ap")? null: (String)rq.getParameter("ap"));
			if(application != null && application.equals("SSO")) {
				System.out.println("## SSO Authen Here ##");
				String username = (null == rq.getParameter("un")? null: (String)rq.getParameter("un"));
				String password = (null == rq.getParameter("pw")? null: (String)rq.getParameter("pw"));
				System.out.println("## Request Parameters [un = "+ username +", pw  = "+ password +", ap = "+ application +"]");
				doAutoLogin(req, username, password);
			}
			fc.doFilter(rq, rs);
		}
	}

	public static void doAutoLogin(HttpServletRequest req, String username, String password) {
	
//	    	Authentication authenticate = new UsernamePasswordAuthenticationToken(username, password);
//	    	Collection<? extends GrantedAuthority> authorities = authenticate.getAuthorities();
//	    	String grantedUserAuthority = null;
//	    	for(GrantedAuthority grantedAuthority : authorities ) {
//	    		grantedUserAuthority = grantedAuthority.getAuthority();
//		    }
			System.out.println("doAutoLogin");
			// Default Role End User.
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(AUTHORITY_AGENT);
			// Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
			token.setDetails(new WebAuthenticationDetails(req));
			System.out.println("Logging in with = "+ token.getPrincipal());
			
			SecurityContextHolder.getContext().setAuthentication(token);
		
	}

	public static class HttpInfo { public int errorCode = 0; }

	@Configuration
	@EnableWebMvc
	@EnableSwagger2
	@ComponentScan(basePackages = { "th.net.cat.epis.controller", "org.springframework.data.rest.webmvc.halbrowser" })
	@Import(RepositoryRestMvcConfiguration.class)
	public static class WebConfiguration extends WebMvcConfigurerAdapter {

		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			configurer.ignoreAcceptHeader(true).defaultContentType(APPLICATION_JSON);
		}
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			converters.add(new MappingJackson2HttpMessageConverter(mapper));
		}
		@Override
		public void addFormatters(FormatterRegistry formatterRegistry) {
			formatterRegistry.addConverter(new Converter<String, Date>() {
				@Override
				public Date convert(String source) {
					try {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
						simpleDateFormat.setLenient(false);
						return simpleDateFormat.parse(source);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
			});
			formatterRegistry.addConverter(new Converter<String, Date>() {
				@Override
				public Date convert(String source) {
					try {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
						simpleDateFormat.setLenient(false);
						return simpleDateFormat.parse(source);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
			});
		}
		@Bean
		public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
			ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
			resolver.setContentNegotiationManager(manager);

			// Define all possible view resolvers
			List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
			resolvers.add(jspViewResolver());
			resolver.setViewResolvers(resolvers);

			List<View> defaultViews = new ArrayList<View>();
			defaultViews.add(new MappingJackson2JsonView());
			resolver.setDefaultViews(defaultViews);
			return resolver;
		}
		@Bean
		public ViewResolver jspViewResolver() {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/pages/");
			viewResolver.setSuffix(".jsp");
			viewResolver.setOrder(2);
			return viewResolver;
		}
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry
					.addResourceHandler("/pages/resources/**")
					.addResourceLocations(
							"/option/"
							,"/bootstrap-table-1.8.1/"
							,"/bootstrap-validator-0.9.0/"
							,"/images/"
							,"/commons/")
					.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
			registry.addResourceHandler("/jnlp/**").addResourceLocations("/jnlp/");
			registry
					.addResourceHandler("swagger-ui.html")
					.addResourceLocations("classpath:/META-INF/resources/");
			registry
					.addResourceHandler("/webjars/**")
					.addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}

	/*
	 * Please make sure that "INSERT" & "UPDATE" transactions must be placed in the "th.net.cat.epis.service" package.
	 */
	@Configuration
	@Profile("default")
	@EnableTransactionManagement(proxyTargetClass=true)
	@EnableAsync
	@EnableScheduling
	@ComponentScan(basePackages = { "th.net.cat.epis.service", "th.net.cat.epis.report", "th.net.cat.epis.batch","th.net.cat.epis.ws.service", "th.net.cat.epis.initial" })
	@ImportResource({"classpath:applicationContext.xml", "classpath:client-beans.xml", "classpath:cxf.xml"})
	public static class RootContextConfig {

		@Autowired Environment env; // e.g. spring.profile.active
//		EPIS DB
		@Value("${jdbc.epis.driver}") String jdbcEpisDriver;
		@Value("${jdbc.epis.url}") String jdbcEpisUrl;
		@Value("${jdbc.epis.username}") String jdbcEpisUsername;
		@Value("${jdbc.epis.password}") String jdbcEpisPassword;
//		CRM DB
		@Value("${jdbc.viewcrm.driver}") String jdbcViewCRMDriver;
		@Value("${jdbc.viewcrm.url}") String jdbcViewCRMUrl;
		@Value("${jdbc.viewcrm.username}") String jdbcViewCRMUsername;
		@Value("${jdbc.viewcrm.password}") String jdbcViewCRMPassword;
//		Billing DB
		@Value("${jdbc.billing.driver}") String jdbcBillingDriver;
		@Value("${jdbc.billing.url}") String jdbcBillingUrl;
		@Value("${jdbc.billing.username}") String jdbcBillingUsername;
		@Value("${jdbc.billing.password}") String jdbcBillingPassword;

		@Bean
		public DataSource episDataSource() throws SQLException {
			th.net.cat.epis.EpDataSource dataSource = new th.net.cat.epis.EpDataSource();
			dataSource.setURL(jdbcEpisUrl);
			dataSource.setUser(jdbcEpisUsername);
			dataSource.setPassword(jdbcEpisPassword);
			return dataSource;
		}
		@Bean
		public DataSource viewCrmDataSource() throws SQLException {
			th.net.cat.epis.EpDataSource dataSource = new th.net.cat.epis.EpDataSource();
			dataSource.setURL(jdbcViewCRMUrl);
			dataSource.setUser(jdbcViewCRMUsername);
			dataSource.setPassword(jdbcViewCRMPassword);
			return dataSource;
		}
		@Bean
		public DataSource billingDataSource() throws SQLException {
			th.net.cat.epis.EpDataSource dataSource = new th.net.cat.epis.EpDataSource();
			dataSource.setURL(jdbcBillingUrl);
			dataSource.setUser(jdbcBillingUsername);
			dataSource.setPassword(jdbcBillingPassword);
			return dataSource;
		}
		@Bean @Primary
		public JdbcTemplate episJdbcTemplate(@Qualifier("episDataSource") DataSource dataSource) throws SQLException {
			return new JdbcTemplate(dataSource);
		}
		
		@Bean
		public NamedParameterJdbcTemplate episNamedParamJdbcTemplate(@Qualifier("episDataSource") DataSource dataSource) throws SQLException {
			return new NamedParameterJdbcTemplate(dataSource);
		}
		
		@Bean
		public JdbcTemplate viewCrmJdbcTemplate(@Qualifier("viewCrmDataSource") DataSource dataSource) throws SQLException {
			return new JdbcTemplate(dataSource);
		}
		@Bean
		public JdbcTemplate billingJdbcTemplate(@Qualifier("billingDataSource") DataSource dataSource) throws SQLException {
			return new JdbcTemplate(dataSource);
		}
		
		@Bean
		public NamedParameterJdbcTemplate billingNamedParamJdbcTemplate(@Qualifier("billingDataSource") DataSource dataSource) throws SQLException {
			return new NamedParameterJdbcTemplate(dataSource);
		}
		
		@Bean
		public RepositoryRestConfigurerAdapter repositoryRestConfigurer() {
			return new RepositoryRestConfigurerAdapter() {
				@Override
				public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
					// Access URL: http://localhost:8088/cat-epis-web/service/browser/index.html
					config.setBasePath("/service");
					config.setDefaultPageSize(200);
					config.setMaxPageSize(50000);
					config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
					Set<Class<?>> classes = new HashSet<Class<?>>();
					classes.addAll(new Reflections("th.net.cat.epis.entity").getTypesAnnotatedWith(Entity.class));
					classes.addAll(new Reflections("th.net.cat.crm.entity").getTypesAnnotatedWith(Entity.class));
					classes.addAll(new Reflections("th.net.cat.erp.entity").getTypesAnnotatedWith(Entity.class));
					classes.addAll(new Reflections("th.net.cat.billing.entity").getTypesAnnotatedWith(Entity.class));
					config.exposeIdsFor(classes.toArray(new Class[0]));
				}
			};
		}
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			// Reference: System.getProperty("java.home") +"/lib/tzdb.dat"
			TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok")); // if no operating system time zone will be used.
			String profile = System.getProperty("spring.profile.active", "sit");
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(new ClassPathResource[] { new ClassPathResource(profile+ ".properties") });
			return pspc;
		}
	}

	/*
	# logs the SQL statements
	log4j.logger.org.hibernate.SQL=debug
	# Logs the JDBC parameters passed to a query
	log4j.logger.org.hibernate.type=trace
	*/
	@EnableJpaRepositories(basePackages = { "th.net.cat.epis.repo", "th.net.cat.erp.repo" }, entityManagerFactoryRef="episEntityManagerFactory")
	public static class EpisRepositoryConfiguration {
		@Bean // @PersistenceContext(unitName="episEntityManagerFactory") EntityManager em;
		@Primary
		public LocalContainerEntityManagerFactoryBean episEntityManagerFactory(@Qualifier("episDataSource") DataSource dataSource) throws SQLException {
			Properties jpaProperties = new Properties();
//			jpaProperties.put("hibernate.hbm2ddl.auto", "validate"); // create-drop
			jpaProperties.put("hibernate.dialect", org.hibernate.dialect.Oracle10gDialect.class.getName());
			jpaProperties.put("hibernate.show_sql", "true");
			jpaProperties.put("hibernate.format_sql", "true");
			/*
			jpaProperties.put("hibernate.connection.characterEncoding", "utf8");
			jpaProperties.put("hibernate.connection.CharSet", "utf8");
			jpaProperties.put("hibernate.connection.useUnicode", "true");
			*/
			HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
			jpaVendorAdapter.setShowSql(true);
			jpaVendorAdapter.setDatabase(Database.ORACLE);
			LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactory.setDataSource(dataSource);
			entityManagerFactory.setPackagesToScan("th.net.cat.epis.entity", "th.net.cat.erp.entity");
			entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
			entityManagerFactory.setJpaProperties(jpaProperties);
			return entityManagerFactory;
		}
		/*
		 log4j.logger.org.springframework.orm.jpa=DEBUG
		 log4j.logger.org.springframework.transaction=DEBUG
		 */
		@Bean(name = "transactionManager")
		@Primary
		public PlatformTransactionManager transactionManager(@Qualifier("episEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(entityManagerFactory);
			return transactionManager;
		}
	}

	@EnableJpaRepositories(basePackages = { "th.net.cat.crm.repo" }, entityManagerFactoryRef="viewCrmEntityManagerFactory")
	public static class ViewCrmRepositoryConfiguration {
		@Bean
		public LocalContainerEntityManagerFactoryBean viewCrmEntityManagerFactory(@Qualifier("viewCrmDataSource") DataSource dataSource) throws SQLException {
			Properties jpaProperties = new Properties();
//			jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
			jpaProperties.put("hibernate.dialect", org.hibernate.dialect.Oracle10gDialect.class.getName());
			jpaProperties.put("hibernate.show_sql", "true");
			jpaProperties.put("hibernate.format_sql", "true");
			jpaProperties.put("hibernate.default_schema", "crmdata");
			HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
			jpaVendorAdapter.setShowSql(true);
			jpaVendorAdapter.setDatabase(Database.ORACLE);
			LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactory.setDataSource(dataSource);
			entityManagerFactory.setPackagesToScan("th.net.cat.crm.entity");
			entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
			entityManagerFactory.setJpaProperties(jpaProperties);
			return entityManagerFactory;
		}
	}

	@EnableJpaRepositories(basePackages = { "th.net.cat.billing.repo" }, entityManagerFactoryRef="billingEntityManagerFactory",transactionManagerRef = "billingTransactionManager")
	public static class BillingRepositoryConfiguration {
		@Bean
		public LocalContainerEntityManagerFactoryBean billingEntityManagerFactory(@Qualifier("billingDataSource") DataSource dataSource) throws SQLException {
			Properties jpaProperties = new Properties();
			jpaProperties.put("hibernate.dialect", org.hibernate.dialect.Oracle10gDialect.class.getName());
			jpaProperties.put("hibernate.show_sql", "true");
			jpaProperties.put("hibernate.format_sql", "true");
			jpaProperties.put("hibernate.default_schema", "pay_epis");
			HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
			jpaVendorAdapter.setShowSql(true);
			jpaVendorAdapter.setDatabase(Database.ORACLE);
			LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactory.setDataSource(dataSource);
			entityManagerFactory.setPackagesToScan("th.net.cat.billing.entity");
			entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
			entityManagerFactory.setJpaProperties(jpaProperties);
			return entityManagerFactory;
		}
		
		@Bean(name ="billingTransactionManager")
		public PlatformTransactionManager billingTransactionManager(@Qualifier("billingEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(entityManagerFactory);
			return transactionManager;
		}
	}

	public static class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {}

	@Configuration
	@EnableWebSecurity
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Resource(name="episJdbcTemplate") JdbcTemplate jdbcTemplate;
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth, @Qualifier("episDataSource") DataSource dataSource) throws Exception {
//			auth
//			.inMemoryAuthentication()
//			.withUser("EPIS").password("password").roles("USER");
			auth
					.jdbcAuthentication()
					.passwordEncoder(new Md5PasswordEncoder())
					.dataSource(dataSource)
					//.usersByUsernameQuery("SELECT offi.username as username, auth.password as password, CASE WHEN offi.ispositive = 1 THEN 1 ELSE 0 END as enabled FROM ARCUSERAUTHNTICN auth LEFT JOIN MASOFFICER offi ON offi.officerid = auth.officerid WHERE offi.username = ?")
					.usersByUsernameQuery("SELECT offi.username as username, offi.password as password, CASE WHEN offi.ispositive = 1 THEN 1 ELSE 0 END as enabled  FROM MASOFFICER offi WHERE username= ?")
					//.authoritiesByUsernameQuery("SELECT offi.username as username, prin.name as authority FROM ARCUSERAUTHNTICN auth LEFT JOIN MASOFFICER offi ON offi.officerid = auth.officerid LEFT JOIN ARCPRINCIPAL prin ON prin.principalid = offi.principalid where offi.username = ?");
					.authoritiesByUsernameQuery("SELECT offi.username as username, prin.name as authority FROM MASOFFICER offi LEFT JOIN ARCPRINCIPAL prin ON prin.principalid = offi.principalid WHERE offi.USERNAME = ?");
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.addFilterBefore(new CharsetFilter(), AnonymousAuthenticationFilter.class)
					.addFilterAfter(new CheckSSOLoggedInFilter(), AnonymousAuthenticationFilter.class)
					.addFilterAfter(new CheckLoggedInFilter(), AnonymousAuthenticationFilter.class)
					.addFilterAfter(new CheckRequestUriFilter(), FilterSecurityInterceptor.class)
					.addFilterAfter(ContextHolderFilter.init(jdbcTemplate), CheckRequestUriFilter.class);
			http.sessionManagement().maximumSessions(1)/*.maxSessionsPreventsLogin(true)*/;
			http.headers().frameOptions().disable().and().csrf().disable()
					.authorizeRequests()
					.antMatchers("/jnlp/**").permitAll()
					.antMatchers("/**/*.css").permitAll()
					.antMatchers("/**/*.js").permitAll()
					.antMatchers("/**/*.png").permitAll()
					.antMatchers("/**/*.ttf").permitAll()
					.antMatchers("/pages/**").authenticated()//.hasAnyAuthority(AUTHORITY_BASIC, AUTHORITY_AGENT, AUTHORITY_GFMISAGENT, AUTHORITY_ADMIN,AUTHORITY_SUP,AUTHORITY_TRANSFER,AUTHORITY_TRANSIN)
					//.antMatchers("/admin/**").hasAnyAuthority(AUTHORITY_ADMIN)
					//.antMatchers("/admin/**").authenticated()
					.anyRequest().authenticated()
					.and().formLogin()
					.loginPage(PAGE_LOGIN)
					.loginProcessingUrl("/pages/login")
					.failureUrl(PAGE_LOGIN +"?wrongAuthen")
//			.defaultSuccessUrl(PAGE_MAIN, true)
					.successHandler(new AuthenticationSuccessHandler() {
						@Override
						public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
							String contextPath = req.getContextPath();
							org.springframework.security.core.GrantedAuthority[] grantedAuthorities = auth.getAuthorities().toArray(new org.springframework.security.core.GrantedAuthority[0]);
							if (grantedAuthorities == null || grantedAuthorities.length < 1) {
								res.sendRedirect(contextPath + PAGE_LOGIN);
								return;
							}/* else if ("ADMIN".equals(grantedAuthorities[0].getAuthority())) {
								res.sendRedirect(contextPath + PAGE_MAIN_ADMIN);
								return;
							}*/
							//res.sendRedirect(contextPath + PAGE_MAIN_AGENT);///move to home controller
							res.sendRedirect(contextPath+PAGE_HOME_CONTROLLER);

						}
					})
					.failureHandler(new AuthenticationFailureHandler() {
						@Override
						public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth) throws IOException, ServletException {
							String contextPath = req.getContextPath();
							res.sendRedirect(contextPath + PAGE_LOGIN);
							return;
						}
					})
					.permitAll()
					.and().logout().invalidateHttpSession(true).logoutUrl("/pages/logout");
		}
	}

	@Configuration
	//@EnableBatchProcessing
	@ImportResource("classpath:applicationBatch.xml")
	public static class ContextBatch {

		@Autowired JobLauncher jobLauncher;
		/* */
//		@Resource(name="jobEPIS001") Job jobEPIS001; // Retry Create WriteOff, Billing
//		@Resource(name="jobEPIS002") Job jobEPIS002;
//
//		@Resource(name="jobEPIS003") Job jobEPIS003; // Credit Limit Data.
//
//		@Resource(name="jobEPIS004") Job jobEPIS004; // Receipt Offline Data.
//		
		@Resource(name="jobEPIS005") Job jobEPIS005; // collect data to end day closing
//
//		@Resource(name="jobInvoiceLockTask") Job jobInvoiceLockTask; // Clear invoice Lock.
//		@Resource(name="jobInvoiceSummaryTask") Job jobInvoiceSummaryTask; // Clear invoice Lock.
		
//		@Resource(name="generateDailySAPFileJob") Job generateDailySAPFileJob;
		/**/
		@Bean
		public BatchConfigurer configurer(@Qualifier("episDataSource") DataSource dataSource) {
			return new DefaultBatchConfigurer(dataSource) {
				@Override
				public void setDataSource(DataSource dataSource) {
					super.setDataSource(dataSource);
				}
			};
		}
		@Bean
		public JobLauncher jobLauncher(BatchConfigurer batchConfigurer) throws Exception {
			return batchConfigurer.getJobLauncher();
		}
		@Bean
		public JobRepository jobRepository(@Qualifier("episDataSource") DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
			JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
			jobRepositoryFactoryBean.setDatabaseType(DatabaseType.ORACLE.getProductName());
			jobRepositoryFactoryBean.setDataSource(dataSource);
			jobRepositoryFactoryBean.setTransactionManager(transactionManager);
			jobRepositoryFactoryBean.setMaxVarCharLength(2500);
			return jobRepositoryFactoryBean.getObject();
		}
		/* */
		
//		@Scheduled(cron="0 */2 * * * ?")
//	    public void generateDailySAPFile() throws Exception{
//	    	JobParametersBuilder builder = new JobParametersBuilder();
//	    	builder.addDate("date", new Date());
//	    	jobLauncher.run(generateDailySAPFileJob, builder.toJobParameters());
//	    }
		
//	    @Scheduled(cron="0 0/2 * * * ?")
//	    public void retryIncompleteReceiptPayment() throws Exception{
//	    	JobParametersBuilder builder = new JobParametersBuilder();
//	    	builder.addDate("date", new Date());
//	    	jobLauncher.run(jobEPIS001, builder.toJobParameters());
//	    }
//
////	    @Scheduled(cron="0 0 21 * * ?")
//		@Scheduled(cron="0 */3 * * * ?")
//	    public void exportDailySummarySAPFile() throws Exception{
//	    	JobParametersBuilder builder = new JobParametersBuilder();
//	    	builder.addDate("date", new Date());
//	    	jobLauncher.run(jobEPIS002, builder.toJobParameters());
//	    }
//
//	    //@Scheduled(cron="0 0/30 * * * ?")
//	    @Scheduled(cron="0 0/3 * * * ?")
//	    public void exportDailySummaryCreditLimitFile() throws Exception {
//	    	JobParametersBuilder builder = new JobParametersBuilder();
//	    	builder.addDate("date", new Date());
//	    	jobLauncher.run(jobEPIS003, builder.toJobParameters());
//	    }
//
//	    @Scheduled(cron="0 0/30 * * * ?")
//	    public void importPaymentAndRecepitOfflineFile() throws Exception {
//	    	JobParametersBuilder builder = new JobParametersBuilder();
//	    	builder.addDate("date", new Date());
//	    	jobLauncher.run(jobEPIS004, builder.toJobParameters());
//	    }
//
//		//@Value("${epis.clear.invoice.lock.interval.time}") String intervalTime;
//		@Scheduled(cron="0 0/5 * * * ?")
//		public void clearInvoiceLock() throws Exception {
//			JobParametersBuilder builder = new JobParametersBuilder();
//			builder.addString("intervalTime","5");
//			jobLauncher.run(jobInvoiceLockTask, builder.toJobParameters());
//		}
//
//		@Scheduled(cron="0 0 0 * * ?")
//		public void checkinvoiceSummary() throws Exception {
//			JobParametersBuilder builder = new JobParametersBuilder();
//			jobLauncher.run(jobInvoiceSummaryTask, builder.toJobParameters());
//		}
		 @Scheduled(cron="0 0/3 * * * ?")
	        public void collectEndDayClosing() throws Exception {
	            JobParametersBuilder builder = new JobParametersBuilder();
	            builder.addDate("date", new Date());
	            jobLauncher.run(jobEPIS005, builder.toJobParameters());
	        }
      /* */
	}

	@Configuration
	@EnableIntegration
	@ComponentScan("th.net.cat.epis.integration")
	@IntegrationComponentScan("th.net.cat.epis.integration")
	@ImportResource("classpath:applicationIntegration.xml")
	public static class ContextIntegration {

		@Bean
		public SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory() {
			DefaultSftpSessionFactory defaultSftpSessionFactory = new DefaultSftpSessionFactory();
			defaultSftpSessionFactory.setHost("10.36.10.16");
			defaultSftpSessionFactory.setPort(22);
			defaultSftpSessionFactory.setUser("epis_user");
			defaultSftpSessionFactory.setPassword("password021166615");
			defaultSftpSessionFactory.setAllowUnknownKeys(true);
			return defaultSftpSessionFactory;
		}
		@Bean
		public MessageChannel sapFileChannel() {
			return new DirectChannel();
		}
		
		@Bean
		public ExcelTransformer excelTransformer( ) {
			return new ExcelTransformer();
		}
		
	   @Bean
	   public MultipartResolver multipartResolver() {
	      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	      multipartResolver.setMaxUploadSize(10485760); // 10MB
	      multipartResolver.setMaxUploadSizePerFile(5242880); // 5MB
	      return multipartResolver;
	   }
		
//		@Bean
//		public JexlEngine JexlEngine() {
//			return new JexlEngine();
//		}
		
	}
}