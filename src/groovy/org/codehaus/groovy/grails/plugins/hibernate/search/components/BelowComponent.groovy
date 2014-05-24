package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query
import org.hibernate.search.query.dsl.RangeMatchingContext

class BelowComponent extends Leaf<RangeMatchingContext> {
	def below

	Query createQuery( RangeMatchingContext fieldCustomization ) { fieldCustomization.below( below ).createQuery() }

    RangeMatchingContext createFieldCustomization( ) { queryBuilder.range().onField( field ) }
}


