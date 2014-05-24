grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.source.level = 1.6
grails.project.target.level = 1.6

grails.project.dependency.resolution = {
    inherits "global"
    log "warn"
    repositories {
        inherits true
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenCentral()
        mavenRepo "http://repository.codehaus.org"
    }

    dependencies {
        compile('org.hibernate:hibernate-search:4.5.1.Final') {
            excludes "hibernate-core"
        }
    }

    plugins {
//        compile ":hibernate:$grailsVersion"
        compile (":hibernate4:4.3.5.3") { export = false }

        build(":tomcat:7.0.53") {
            export = false
        }

        build ':release:3.0.1', ':rest-client-builder:1.0.3', {
            export = false
        }
    }
}
