package com.upbesports.utils;

public class QueryUtils 
{
	public static String addFilter(String filterText, Object filterValue, boolean useAnd, String filters)
	{
		return filterText + " " + filterValue + (useAnd ? " and " : " or ") + filters;
	}
}
