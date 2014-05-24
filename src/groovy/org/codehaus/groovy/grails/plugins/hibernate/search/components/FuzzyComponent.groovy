package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query
import org.hibernate.search.query.dsl.FuzzyContext
import org.hibernate.search.query.dsl.TermMatchingContext

class FuzzyComponent extends Leaf<TermMatchingContext> {
	def matching
	Float threshold

	Query createQuery( TermMatchingContext fieldCustomization ) { fieldCustomization.matching( matching ).createQuery() }

    TermMatchingContext createFieldCustomization( ) {
		FuzzyContext context = queryBuilder.keyword().fuzzy()
		if (threshold) { context.withThreshold( threshold ) }
		context.onField( field )
    }
}
