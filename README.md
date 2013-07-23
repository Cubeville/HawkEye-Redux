HawkEye Redux
=============

HawkEye Redux is a complete rewrite of the [original plugin](https://github.com/oliverwoodings/HawkEye) for Bukkit.  Features include:

* Logging of 30+ different actions
* Advanced search parameters to let you specify exactly what you want to know
* Easy to use tool block for finding changes to a single block quickly
* Queued rollbacks to ensure your server runs smoothly all the way through
* Asynchronous database operations reduce lag to a minimum

Compilation
-----------

HawkEye Redux is build on Java 6 through [Maven](http://maven.apache.org/).  Dependencies will be automatically handled by Maven.

* Install [Maven 3](http://maven.apache.org/download.cgi)
* Checkout this repo
* Run `mvn clean install`

Contribution Guidelines
-----------------------

* Please use **tabs** for indentation.
* Code must compile under **Java 6**.
* Provide **JavaDocs** wherever possible.
* Test your code **before** submitting a pull request!
* Make use of unit tests where possible.
    * While not required, they're generally recommended for any complicated algorithms
* Keep commits to a minimum, [`git rebase`](https://help.github.com/articles/interactive-rebase) is your friend!
* Group imports by package and keep them in alphabetical order.
    **Example:**
    
    ```java
    import java.io.File;
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    
    import org.bukkit.command.Command;
    import org.bukkit.command.CommandSender;
    import org.bukkit.event.EventHandler;
    import org.bukkit.event.EventPriority;
    import org.bukkit.event.Listener;
    import org.bukkit.plugin.java.JavaPlugin;
    
    import org.cubeville.hawkeye.HawkEyeEngine;
    import org.cubeville.hawkeye.PluginEngine;
    import org.cubeville.hawkeye.config.HawkEyeConfig;
    import org.cubeville.util.StringUtil;
    ```