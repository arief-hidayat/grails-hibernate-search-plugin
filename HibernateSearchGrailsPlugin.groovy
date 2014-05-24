import org.codehaus.groovy.grails.commons.ClassPropertyFetcher
import org.codehaus.groovy.grails.orm.hibernate.GrailsHibernateTemplate
import org.codehaus.groovy.grails.orm.hibernate.GrailsHibernateTemplate.HibernateCallback
import org.codehaus.groovy.grails.plugins.hibernate.search.HibernateSearchConfig
import org.codehaus.groovy.grails.plugins.hibernate.search.HibernateSearchQueryBuilder
import org.codehaus.groovy.grails.plugins.hibernate.search.SearchMappingConfigurableLocalSessionFactoryBean
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.search.annotations.Indexed
import org.springframework.core.annotation.AnnotationUtils

class HibernateSearchGrailsPlugin {
    def version = "0.9-SNAPSHOT"
    def grailsVersion = "2.0 > *"
    def loadAfter = ['hibernate']
    def title = "Hibernate Search Plugin"
    def author = "Mathieu Perez, Julie Ingignoli"
    def authorEmail = "mathieu.perez@novacodex.net, julie.ingignoli@novacodex.net"
    def description = 'Integrates Hibernate Search features to Grails'
    def documentation = "http://grails.org/plugin/hibernate-search"
    def license = 'APACHE'
    def organization = [name: 'NovaCodex', url: 'http://www.novacodex.net/']
    def developers = [[name: 'Mathieu Perez', email: 'mathieu.perez@novacodex.net'],
            [name: 'Julie Ingignoli', email: 'julie.ingignoli@novacodex.net']]
    def issueManagement = [system: 'github', url: 'https://github.com/mathpere/grails-hibernate-search-plugin/issues']
    def scm = [url: 'https://github.com/mathpere/grails-hibernate-search-plugin']

    def doWithSpring = {
        sessionFactory( SearchMappingConfigurableLocalSessionFactoryBean ) { bean ->
            // see org.codehaus.groovy.grails.plugins.orm.hibernate.HibernatePluginSupport:
            bean.parent = 'abstractSessionFactoryBeanConfig'
        }
    }

    def doWithDynamicMethods = { ctx ->

        SessionFactory sessionFactory = ctx.sessionFactory

        GrailsHibernateTemplate hibernateTemplate = new GrailsHibernateTemplate( sessionFactory )



        // add search() method to indexed domain classes:
        application.domainClasses.each { grailsClass ->
            def clazz = grailsClass.clazz

            if ( ClassPropertyFetcher.forClass( clazz ).getStaticPropertyValue( "search", Closure ) || AnnotationUtils.isAnnotationDeclaredLocally( Indexed, clazz ) ) {

                grailsClass.metaClass.static.search = {
                    hibernateTemplate.execute( { Session session ->
                        new HibernateSearchQueryBuilder( clazz, session )
                    } as HibernateCallback )
                }

                grailsClass.metaClass.search = {
                    def instance = delegate
                    hibernateTemplate.execute( { Session session ->
                        new HibernateSearchQueryBuilder( clazz, instance, session )
                    } as HibernateCallback )
                }
            }
        }

        // config
        def hibernateSearchConfig = application.config.grails.plugins.hibernatesearch

        if ( hibernateSearchConfig && hibernateSearchConfig instanceof Closure ) {

            hibernateTemplate.execute( { Session session ->

                hibernateSearchConfig.delegate = new HibernateSearchConfig( session )
                hibernateSearchConfig.resolveStrategy = Closure.DELEGATE_FIRST
                hibernateSearchConfig.call()

            } as HibernateCallback )

        }

    }
}
