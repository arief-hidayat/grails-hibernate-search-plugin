package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query
import org.hibernate.search.query.dsl.PhraseMatchingContext

class PhraseComponent extends Leaf<PhraseMatchingContext> {
	String sentence

	Query createQuery( PhraseMatchingContext fieldCustomization ) { fieldCustomization.sentence( sentence ).createQuery() }

    PhraseMatchingContext createFieldCustomization( ) { queryBuilder.phrase().onField( field ) }
}


