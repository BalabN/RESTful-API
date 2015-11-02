package org.knedl.example.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.knedl.example.messenger.datebase.DatabaseClass;
import org.knedl.example.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	// Constructor da imamo s nečim počet
	public ProfileService() {
		profiles.put("Pik", new Profile(1L, "pik", "Nikola", "Balaban"));
	}
	
	// Get all profiles
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	// Get one profile from profile name
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	// Add new profile
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	// Update existing profile
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	// Delete existing profile
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
	
}
