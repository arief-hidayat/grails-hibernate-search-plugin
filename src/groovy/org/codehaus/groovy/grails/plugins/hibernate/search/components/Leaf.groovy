package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query
import org.hibernate.search.query.dsl.FieldCustomization

abstract class Leaf<T extends FieldCustomization> extends Composite {
	protected String field

	def ignoreAnalyzer = false
	def ignoreFieldBridge = false
	Float boostedTo

	final def leftShift( component ) {
		throw new UnsupportedOperationException( "${this.class.name} is a leaf" )
	}

	final Query createQuery( ) {
		def fieldCustomization = createFieldCustomization()

		if ( ignoreAnalyzer ) { fieldCustomization = fieldCustomization.ignoreAnalyzer() }

		if ( ignoreFieldBridge ) { fieldCustomization = fieldCustomization.ignoreFieldBridge() }

		if ( boostedTo ) { fieldCustomization = fieldCustomization.boostedTo( boostedTo ) }

		createQuery( fieldCustomization )
	}

	abstract Query createQuery( T fieldCustomization )

	abstract T createFieldCustomization( )
}
