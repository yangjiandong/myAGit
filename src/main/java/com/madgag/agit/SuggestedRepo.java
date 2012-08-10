package com.madgag.agit;


import static java.util.Arrays.asList;

import java.util.List;

public class SuggestedRepo {
    private final String name;
    private final String uri;

    public SuggestedRepo(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getURI() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public static List<SuggestedRepo> SUGGESTIONS = asList(
            new SuggestedRepo("ConnectBot", "https://code.google.com/p/connectbot/"), // 3.4M
            new SuggestedRepo("Scalatra", "git://github.com/scalatra/scalatra.git"), //1.6M - not that fast?
            // new SuggestedRepo("Android Music", "git://android.git.kernel.org/platform/packages/apps/Music.git"),
            // //2.0M
            new SuggestedRepo("SBT Android Plugin", "git://github.com/jberkel/android-plugin.git"), // 248K
            new SuggestedRepo("Maven Android Plugin", "git://github.com/jayway/maven-android-plugin.git"), // 3.9M
            new SuggestedRepo("sshj", "git://github.com/shikhar/sshj.git"), // 2.5M
            // new SuggestedRepo("Redis", "git://github.com/antirez/redis.git"), // 3.3M - slow due to deltas
            // new SuggestedRepo("GWT ORM", "git://android.git.kernel.org/tools/gwtorm.git"), //2.9M - currently down
            new SuggestedRepo("JGit", "git://egit.eclipse.org/jgit.git"), // 5.0M
            new SuggestedRepo("git.js", "git://github.com/danlucraft/git.js.git")
    );

    public String toString() {
        return name + " " + uri;
    }
}
