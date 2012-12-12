package vis.filters;

public enum AbstractState {
	OFF,
	HIDE,
	SHOW;
	
	private static String[] strings = new String[]{
		"Удалить",
		"Скрыть",
		"Показать"
	};
	
	public boolean isOFF() {
		return this == OFF;
	}
	public boolean isShown() {
		return this == SHOW;
	}
	public boolean isHidden() {
		return this != SHOW;
	}
	
	public AbstractState min(AbstractState t1, AbstractState t2) {
		return t1.ordinal() > t2.ordinal() ? t2 : t1; 
	}
	public AbstractState max(AbstractState t1, AbstractState t2) {
		return t1.ordinal() < t2.ordinal() ? t2 : t1; 
	}
	
	public AbstractState next() {
		return AbstractState.values()[(ordinal()+1)%values().length];		
	}
	
	public String toString() {
		return strings[ordinal()];
	}
	public static AbstractState fromString(String str) {
		for( int i=0; i<values().length; i++ ) {
			if( strings[i].equals(str) ) {
				return values()[i];
			} 	
		}
		return AbstractState.OFF;
	} 
}
