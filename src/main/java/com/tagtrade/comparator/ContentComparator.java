package com.tagtrade.comparator;

import java.util.Comparator;

import com.tagtrade.service.bean.Content;

public class ContentComparator implements Comparator<Content> {

	@Override
	public int compare(Content o1, Content o2) {
		if (!o1.getDateContentCreate().equals(o2.getDateContentCreate())) {
			return o2.getDateContentCreate().compareTo(o1.getDateContentCreate());
		}

		return 0;
	}

}
