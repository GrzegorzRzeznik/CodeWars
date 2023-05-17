package kyu5.URL_shortener;
import java.util.*;
/*
Background Information
When do we use an URL shortener?
In your PC life you have probably seen URLs like this before:

https://bit.ly/3kiMhkU
If we want to share a URL we sometimes have the problem that it is way too long, for example this URL:

https://www.google.com/search?q=codewars&tbm=isch&ved=2ahUKEwjGuLOJjvjsAhXNkKQKHdYdDhUQ2-cCegQIABAA&oq=codewars&gs_lcp=CgNpbWcQAzICCAAyBAgAEB4yBAgAEB4yBAgAEB4yBAgAEB4yBAgAEB4yBAgAEB4yBAgAEB4yBAgAEB4yBggAEAUQHlDADlibD2CjEGgAcAB4AIABXIgBuAGSAQEymAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=RJmqX8aGHM2hkgXWu7ioAQ&bih=1099&biw=1920#imgrc=Cq0ZYnAGP79ddM
In such cases a URL shortener is very useful.

How does it work?
The URL shortener is given a long URL, which is then converted into a shorter one. Both URLs are stored in a database.
It is important that each long URL is assigned a unique short URL.

If a user then calls up the short URL, the database is checked to see which long URL belongs to this short URL and you are redirected to the original/long URL.

Important Note: Some URLs such as www.google.com are used very often. It can happen that two users want to shorten the same URL,
so you have to check if this URL has been shortened before to save memory in your database.

Task
URL Shortener
Write a function urlShortener(longUrl), which receives a long URL and returns a short URL starting with short.ly/,
consisting only of lowercase letters (and one dot and one slash) and max length of 13.

Note: short.ly/ is not a valid short URL.

Redirect URL
Write a function urlRedirector(shortUrl), which receives the shortened URL and returns the corresponding long URL.

Performance
There are 475_000 random tests. You don't need a complicated algorithm to solve this kata,
but iterating each time through the whole database to check if a URL was used before or generating short URLs based on randomness, won't pass.
 */
public class Shortener {
    private static final Map<String, String> shortLong = new HashMap<>();
    private static final Map<String, String> longShort = new HashMap<>();
    private static final List<Character> lastUrl = new ArrayList<>(List.of('a'));

    public String urlShortener(String longURL) {
        if (longShort.containsKey(longURL)) {
            return longShort.get(longURL);
        }
        String shortURL = "short.ly/" + generateShortUrl();
        shortLong.put(shortURL, longURL);
        longShort.put(longURL, shortURL);
        return shortURL;
    }

    public String urlRedirector(String shortURL) {
        return shortLong.get(shortURL);
    }

    public String generateShortUrl() {
        StringBuilder result = new StringBuilder();
        for (Character c : lastUrl) {
            result.append(c);
        }
        increment();
        return result.toString();

    }

    public void increment() {
        for (int i = 0; i < lastUrl.size(); i++) {
            if (lastUrl.get(i) == 122){
                lastUrl.set(i, 'a');
                if (i == lastUrl.size() - 1){
                    lastUrl.add('a');
                    return;
                }
            }else {
                lastUrl.set(i, (char) (lastUrl.get(i) + 1));
                return;
            }
        }
    }
}