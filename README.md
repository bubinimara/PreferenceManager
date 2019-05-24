# PreferenceManager
Android library to Store and Load objects in `SharedPreferences`<br>

## Install
##### Gradle
`compile 'com.github.bubinimara:preference-manager:1.0'`
##### Maven
```
<dependency>
	<groupId>com.github.bubinimara</groupId>
	<artifactId>preference-manager</artifactId>
	<version>1.0</version>
	<type>pom</type>
</dependency>
```
##### Ivy
```
<dependency org="com.github.bubinimara" name="preference-manager" rev="1.0">
	<artifact name="preference-manager" ext="pom"></artifact>
</dependency>
```

## Example
The library expose the class `PreferenceLoader` to load and store java objects<br>

```
    private PreferenceManager<SessionInfo> preferenceManager;

    // to load
    preferenceManager = PreferenceManager.getPreferenceManager(this, SessionInfo.class);
    SessionInfo sessionInfo = preferenceManager.load();

    // to store
    preferenceLoader.store(new SessionInfo());

    // to clear
    preferenceManager.clear();
```