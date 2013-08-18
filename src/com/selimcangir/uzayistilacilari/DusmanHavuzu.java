package com.selimcangir.uzayistilacilari;

import org.andengine.util.adt.pool.GenericPool;

public class DusmanHavuzu extends GenericPool<Dusman>{
	public static DusmanHavuzu instance;

	public static DusmanHavuzu sharedEnemyPool() {
		if (instance == null)
			instance = new DusmanHavuzu();
		return instance;
	}
	private DusmanHavuzu() {
		super();
	}
	@Override
	protected Dusman onAllocatePoolItem() {
		return new Dusman();
	}
	@Override
	protected void onHandleObtainItem(Dusman pItem) {
		pItem.baslat();
	}
	protected void onHandleRecycleItem(final Dusman d) {
		d.dusmanSekli.setVisible(false);
		d.dusmanSekli.detachSelf();
		d.temizle();
	}
}