package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query
import org.hibernate.search.query.dsl.RangeMatchingContext

class AboveComponent extends Leaf<RangeMatchingContext> {
	def above

	Query createQuery( RangeMatchingContext fieldCustomization ) { fieldCustomization.above( above ).createQuery() }

    RangeMatchingContext createFieldCustomization( ) { queryBuilder.range().onField( field ) }
}
