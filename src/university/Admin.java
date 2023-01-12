package university;

public final class Admin implements ManageNews {
	
	private static final Admin INSTANCE = new Admin();
    
    private Admin() {
    	
    }
    
    public static Admin getInstance() {
    	return INSTANCE;
    }
	
    public boolean addUser(User user) {
    	user.setLogin(generateLogin(user) + "@kbtu.kz");
    	user.setPassword(generatePassword(user));
    	
    	if(UniSystem.getDatabase().getUsers().contains(user))
    		return false;
    	
    	UniSystem.getDatabase().getUsers().add(user);
    	return true;
    }
    
    public boolean removeUser(User user) {
    	if(!UniSystem.getDatabase().getUsers().contains(user))
    		return false;
    	
    	return UniSystem.getDatabase().getUsers().remove(user);
    }

	public boolean reportNews(News news) {
		if(UniSystem.getDatabase().getNews().contains(news))
			return false;
		
		UniSystem.getDatabase().getNews().add(news);
		return true;
	}
	public boolean removeNews(News news) {
		return UniSystem.getDatabase().getNews().remove(news);
	}
    
    public String generateLogin(User user) {
    	char mode = '.';
    	
    	if(user instanceof Student)
    		mode = '_';
    		
    	
    	return user.getName().substring(0, 1).toLowerCase() + mode + user.getSurname().toLowerCase();
    }
    
    public String generatePassword(User user) {
       return generateLogin(user);
    }
}
