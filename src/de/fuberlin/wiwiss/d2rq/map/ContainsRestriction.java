package de.fuberlin.wiwiss.d2rq.map;

import java.util.Map;
import java.util.Set;

import de.fuberlin.wiwiss.d2rq.rdql.NodeConstraint;

/**
 * Restriction which can be chained with another {@link ValueSource} to state
 * that all its values contain a certain string. This is useful because the
 * query engine can exclude sources if a value doesn't contain the string.
 *
 * @author Richard Cyganiak (richard@cyganiak.de)
 * @version $Id: ContainsRestriction.java,v 1.4 2006/09/03 00:08:10 cyganiak Exp $
 */
public class ContainsRestriction implements ValueSource {
	private ValueSource valueSource;
	private String containedValue;
	
	public ContainsRestriction(ValueSource valueSource, String containedValue) {
		this.valueSource = valueSource;
		this.containedValue = containedValue;
	}
	
	public void matchConstraint(NodeConstraint c) {
		this.valueSource.matchConstraint(c);
	}

	public boolean couldFit(String value) {
		if (value == null) {
			return true;
		}
		return value.indexOf(this.containedValue) >= 0 && this.valueSource.couldFit(value);
	}

	public Set getColumns() {
		return this.valueSource.getColumns();
	}

	public Map getColumnValues(String value) {
		return this.valueSource.getColumnValues(value);
	}

	public String getValue(String[] row, Map columnNameNumberMap) {
		return this.valueSource.getValue(row, columnNameNumberMap);
	}
}