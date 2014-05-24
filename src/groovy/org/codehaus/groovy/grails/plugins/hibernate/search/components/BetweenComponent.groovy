package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query
import org.hibernate.search.query.dsl.RangeMatchingContext

class BetweenComponent extends Leaf<RangeMatchingContext> {
	def from
	def to

	Query createQuery( RangeMatchingContext fieldCustomization ) { fieldCustomization.from( from ).to( to ).createQuery() }

    RangeMatchingContext createFieldCustomization( ) { queryBuilder.range().onField( field ) }
}
