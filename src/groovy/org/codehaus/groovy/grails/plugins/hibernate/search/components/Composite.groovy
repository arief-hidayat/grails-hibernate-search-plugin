package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.hibernate.search.query.dsl.QueryBuilder

abstract class Composite extends Component {
	QueryBuilder queryBuilder
	List<Component> children = []

	def leftShift( component ) {
		assert component instanceof Component: "'component' should be an instance of Component"
		component.parent = this
		children << component
	}

	def toString( indent ) {
		[( "-" * indent ) + this.class.simpleName, children.collect { it.toString( indent + 1 ) }].flatten().findAll {it}.join( "\n" )
	}
}
