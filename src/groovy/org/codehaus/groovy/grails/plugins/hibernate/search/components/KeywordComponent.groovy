package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query
import org.hibernate.search.query.dsl.TermMatchingContext

class KeywordComponent extends Leaf<TermMatchingContext> {
    def matching

    Query createQuery( TermMatchingContext fieldCustomization ) { fieldCustomization.matching( matching ).createQuery() }

    TermMatchingContext createFieldCustomization( ) { queryBuilder.keyword().onField( field ) }
}
