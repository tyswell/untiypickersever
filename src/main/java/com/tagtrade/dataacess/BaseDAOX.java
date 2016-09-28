package com.tagtrade.dataacess;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tagtrade.util.BigDecimalUtil;
import com.tagtrade.util.DateUtil;

// Base Before BaseDAO
/*
 * rs.get
 *   - BigDecimal -> getBigDecimal(rs, "name")
 *   - Integer    -> (Integer) rs.getObject("name")
 *   - Date       -> rs.getDate("name")
 *   - String     -> rs.getString("name")
 *   - Timestamp  -> rs.getTimestamp("name")
 *
 */
public class BaseDAOX {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// ****************************************************************************

	protected <T> T getFirst(List<T> list) {
		if (list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/*
	 * Used to construct SQL
	 */
	protected String strs(String... strs) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];

			if (str.length() != 0) {
				if (sb.length() != 0) {
					sb.append(" ");
				}

				sb.append(str);
			}
		}

		return sb.toString();
	}

	protected final String LIKE_STRING = "*";

	protected String buildOptionalWhere(String fieldName, Object value,
			List<Object> param) {
		return buildOptionalWhere(fieldName, value, false, param);
	}

	protected String buildLikeOptionalWhere(String fieldName, Object value,
			List<Object> param) {
		return buildOptionalWhere(fieldName, value, true, param);
	}

	protected String buildOptionalWhere(String fieldName, Object value,
			boolean isPartial, List<Object> param) {
		if (value == null) {
			return "";
		}
		if (isPartial) {
			if (value instanceof String) {
				String stringVal = (String) value;
				if (stringVal.endsWith(LIKE_STRING)) {
					int lastIndex = stringVal.length();
					stringVal = stringVal.substring(0, lastIndex - 1) + "%";
				}
				param.add(stringVal);
				return fieldName + " like ? and ";
			}
		}
		param.add(value);
		return fieldName + " = ? and ";
	}

	protected String stripLastAnd(String whereClause) {
		if (whereClause == null || whereClause.length() <= 3) {
			return whereClause;
		}
		String testCondition = whereClause.substring(whereClause.length() - 5,
				whereClause.length());
		if (testCondition.indexOf("and") > -1) {
			return whereClause.substring(0, whereClause.length() - 4);
		}
		return whereClause;
	}

	protected String buildInStatement(List<?> criteriaList) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("(");
		for (Object criteria : criteriaList) {
			String criteriaString = "";
			if (criteria instanceof java.util.Date) {
				criteriaString = DateUtil
						.formatDateThai((java.util.Date) criteria);
			} else if (criteria instanceof String) {
				criteriaString = "'" + criteria.toString() + "'";
			} else {
				criteriaString = criteria.toString();
			}

			sb.append(criteriaString + ",");
		}
		String inStatement = sb.toString();
		inStatement = inStatement.substring(0, inStatement.lastIndexOf(","))
				+ ")";

		return inStatement;

	}

	// ****************************************************************************

	protected static BigDecimal getBigDecimal(ResultSet rs, int columnIndex)
			throws SQLException {
		return BigDecimalUtil.setScale(rs.getBigDecimal(columnIndex));
	}

	protected static BigDecimal getBigDecimal(ResultSet rs, String columnLabel)
			throws SQLException {
		return BigDecimalUtil.setScale(rs.getBigDecimal(columnLabel));
	}

}
