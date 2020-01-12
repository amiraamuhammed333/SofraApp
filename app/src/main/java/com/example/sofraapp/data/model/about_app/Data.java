package com.example.sofraapp.data.model.about_app;

public class Data{
	private String twitter;
	private String aboutApp;
	private String terms;
	private String facebook;
	private String commission;
	private int id;
	private String instagram;
	private Object bankAccounts;
	private Object commissionsText;

	public void setTwitter(String twitter){
		this.twitter = twitter;
	}

	public String getTwitter(){
		return twitter;
	}

	public void setAboutApp(String aboutApp){
		this.aboutApp = aboutApp;
	}

	public String getAboutApp(){
		return aboutApp;
	}

	public void setTerms(String terms){
		this.terms = terms;
	}

	public String getTerms(){
		return terms;
	}

	public void setFacebook(String facebook){
		this.facebook = facebook;
	}

	public String getFacebook(){
		return facebook;
	}

	public void setCommission(String commission){
		this.commission = commission;
	}

	public String getCommission(){
		return commission;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setInstagram(String instagram){
		this.instagram = instagram;
	}

	public String getInstagram(){
		return instagram;
	}

	public void setBankAccounts(Object bankAccounts){
		this.bankAccounts = bankAccounts;
	}

	public Object getBankAccounts(){
		return bankAccounts;
	}

	public void setCommissionsText(Object commissionsText){
		this.commissionsText = commissionsText;
	}

	public Object getCommissionsText(){
		return commissionsText;
	}

	@Override
 	public String toString(){
		return 
			"CommissionData{" +
			"twitter = '" + twitter + '\'' + 
			",about_app = '" + aboutApp + '\'' + 
			",terms = '" + terms + '\'' + 
			",facebook = '" + facebook + '\'' + 
			",commission = '" + commission + '\'' + 
			",id = '" + id + '\'' + 
			",instagram = '" + instagram + '\'' + 
			",bank_accounts = '" + bankAccounts + '\'' + 
			",commissions_text = '" + commissionsText + '\'' + 
			"}";
		}
}
