package listeners;

import ejb.AdminFacadeRemote;
import ejb.CategoryFacadeRemote;
import ejb.ChannelFacadeRemote;
import ejb.UserFacadeRemote;
import ejb.UserPreferenceFacadeRemote;
import ejb.UserPrivacyFacadeRemote;
import ejb.VisibilityValidationFacadeRemote;
import entity.Admin;
import entity.Category;
import entity.Channel;
import entity.User;
import entity.UserPreference;
import entity.UserPrivacy;
import entity.VisibilityValidation;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utilities.EJBUtility;

public class ContextListener implements ServletContextListener {

    /* visibleURL for Channels */
    private static final String BBC = "news.bbc.co.uk";
    private static final String FACEBOOK = "www.facebook.com";
    private static final String EBAY = "cgi.ebay.com";
    private static final String PIRATEBAY = "thepiratebay.org";
    private static final String RAPIDSHARE = "rapidshare.de";
    private static final String WIKIPEDIA = "en.wikipedia.org";
    private static final String YOUTUBE = "www.youtube.com";

   
    private static final String EVERYONE = "everyone";
    private static final String FRIENDS = "friends";
    private static final String ME = "me";
    private VisibilityValidationFacadeRemote vvfr;
    private CategoryFacadeRemote categoryRemote;
    private VisibilityValidation everyoneOBJ;
    private VisibilityValidation friendsOBJ;
    private VisibilityValidation meOBJ;
    private List<VisibilityValidation> visibilityrecordSet;

    public void contextInitialized(ServletContextEvent sce) {

        /* Testing Purposes */
        System.out.println("ServletContext Created!");

        /* First Time Initialization Code */
        try {
            /* Adding VisibilityValidation Values */
            addVisValValues();

            /* Creating Admin */
            addAdmin();

            /* Add Default Categories */
            addCategories();

            /* Add Default Channels */
            addChannels();

        } catch (NamingException ex) {
            System.out.println("NAMING EXCEPTION");
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext Destroyed!");
    }

    private void addAdmin() throws NamingException {

        UserFacadeRemote userFacadeRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");
        
        List<User> userRecordSet = userFacadeRemote.findAll();

        if (userRecordSet.isEmpty()) {    /* if User Table is Empty */

            System.out.println("USER Table is EMPTY!");

            Date sqlDate = new Date(System.currentTimeMillis());

            getvisibilityObjects();

            User user = new User(everyoneOBJ, sqlDate, "dexterfyp@gmail.com", true, "admin", "admin");
            user.setLandingPage("classic");

            /* Create Default Preferences */
            createAdmin(user);
            createDefaultPreferences(user);

            /* Create Default Privacy */
            createDefaultPrivacy(user);

            
        }
    }

    private void addChannels() throws NamingException {

        /* Get Remote Object */
        ChannelFacadeRemote channelRemote = (ChannelFacadeRemote) EJBUtility.lookup("ChannelFacade");
        
        /* Get RecordSet */
        List<Channel> recordSet = channelRemote.findAll();

        /* Insert Channels */
        if(recordSet.isEmpty()){

            /* Echo to Server Log */
            System.out.println("Channel Table is EMPTY!");

            /* Get Current Date */
            Date sqlDate = new Date(System.currentTimeMillis());

            /* Create Channel Object */
            Channel Bbc = new Channel("BBC", BBC,sqlDate, true);
            Channel Facebook = new Channel("Facebook",FACEBOOK, sqlDate, true);
            Channel EBay = new Channel("EBay",EBAY, sqlDate, true);
            Channel Piratebay = new Channel("Piratebay",PIRATEBAY, sqlDate, true);
            Channel Rapidshare = new Channel("Rapidshare",RAPIDSHARE, sqlDate, true);
            Channel Wikipedia = new Channel("Wikipedia",WIKIPEDIA, sqlDate, true);
            Channel YouTube = new Channel("YouTube",YOUTUBE, sqlDate, true);

            /* Set VisibleURL in Channel Objects */
           /*Commented by rahul on 12 march as now not needed
            Bbc.setVisibleURL(BBC);
            Facebook.setVisibleURL(FACEBOOK);
            EBay.setVisibleURL(EBAY);
            Piratebay.setVisibleURL(PIRATEBAY);
            Rapidshare.setVisibleURL(RAPIDSHARE);
            Wikipedia.setVisibleURL(WIKIPEDIA);
            YouTube.setVisibleURL(YOUTUBE);
            */
            /* Persist to Database */
            channelRemote.create(Bbc);
            channelRemote.create(Facebook);
            channelRemote.create(EBay);
            channelRemote.create(Piratebay);
            channelRemote.create(Rapidshare);
            channelRemote.create(Wikipedia);
            channelRemote.create(YouTube);

            /* Echo to Server Log */
            System.out.println("Channel Table Created!");
        }

    }

    private void createAdmin(User user) throws NamingException{
            AdminFacadeRemote adminFacadeRemote = (AdminFacadeRemote) EJBUtility.lookup("AdminFacade");

            Admin admin = new Admin(user);
            adminFacadeRemote.create(admin);

            System.out.println("Admin Created");
    }

    private void createDefaultPreferences(User userRecord) throws NamingException {

        /* Get Remote Object*/
        UserPreferenceFacadeRemote userPreferenceRemote = (UserPreferenceFacadeRemote) EJBUtility.lookup("UserPreferenceFacade");
        /*edited by rahul establishing remote connection to user*/
        UserFacadeRemote userRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");

        /* Create New Preference Record */
        /*edited by rahul on 28 feb fetching user from database*/
        userRecord=userRemote.findByUserName(userRecord.getUserName());

        UserPreference userPreference = new UserPreference(userRecord, false, 0, 25, false, true);
        userPreferenceRemote.create(userPreference);
    }

    private void createDefaultPrivacy(User userRecord) throws NamingException {

        /* Get Remote Object */
        UserPrivacyFacadeRemote privacyRemote = (UserPrivacyFacadeRemote) EJBUtility.lookup("UserPrivacyFacade");
        /*edited by rahul establishing remote connection to user*/
        UserFacadeRemote userRemote = (UserFacadeRemote) EJBUtility.lookup("UserFacade");

        /* Create UserPrivacy Object */
        /*edited by rahul on 28 feb fetching user from database*/
        userRecord=userRemote.findByUserName(userRecord.getUserName());

        UserPrivacy userPrivacy = new UserPrivacy(userRecord, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ, everyoneOBJ);

        privacyRemote.create(userPrivacy);

    }

    private void addVisValValues() throws NamingException {

        vvfr = (VisibilityValidationFacadeRemote) EJBUtility.lookup("VisibilityValidationFacade");

        visibilityrecordSet = vvfr.findAll();

        if (visibilityrecordSet.isEmpty()) {    /* if VisibleValidationTable is empty */

            System.out.println("VV Table is EMPTY!");
            Date sqlDate = new Date(System.currentTimeMillis());

            everyoneOBJ = new VisibilityValidation(sqlDate, true, EVERYONE);
            friendsOBJ = new VisibilityValidation(sqlDate, true, FRIENDS);
            meOBJ = new VisibilityValidation(sqlDate, true, ME);

            vvfr.create(everyoneOBJ);
            vvfr.create(friendsOBJ);
            vvfr.create(meOBJ);

            System.out.println("Entries Created in VVTable");

        }

    }

    private void addParentCategories(Date sqlDate) {
        /* Create Parent Categories */
        Category technology = new Category("Technology", sqlDate, true);
        Category world = new Category("World", sqlDate, true);
        Category science = new Category("Science", sqlDate, true);
        Category lifestyle = new Category("Lifestyle", sqlDate, true);
        Category entertainment = new Category("Entertainment", sqlDate, true);
        Category sports = new Category("Sports", sqlDate, true);
        Category offbeat = new Category("Offbeat", sqlDate, true);
        categoryRemote.create(technology);
        categoryRemote.create(world);
        categoryRemote.create(science);
        categoryRemote.create(lifestyle);
        categoryRemote.create(entertainment);
        categoryRemote.create(sports);
        categoryRemote.create(offbeat);
    }

    private void addCategories() throws NamingException {

        /* Get Remote Object */
        categoryRemote = (CategoryFacadeRemote) EJBUtility.lookup("CategoryFacade");

        /* Get Recordset */
        List<Category> categoryRecordset = categoryRemote.findAll();

        /* Insert Categories */
        if (categoryRecordset.isEmpty()) {    // if recordSet is empty

            System.out.println("Category Table is EMPTY!");
            Date sqlDate = new Date(System.currentTimeMillis());
            addParentCategories(sqlDate);
            Category technology = categoryRemote.findByName("Technology");
            Category world = categoryRemote.findByName("World");
            Category science = categoryRemote.findByName("Science");
            Category lifestyle = categoryRemote.findByName("Lifestyle");
            Category entertainment = categoryRemote.findByName("Entertainment");
            Category sports = categoryRemote.findByName("Sports");
            Category offbeat = categoryRemote.findByName("Offbeat");

            /* Create Technology Categories */
            Category linux = new Category("Linux", sqlDate, true);
            Category osx = new Category("OSX", sqlDate, true);
            Category windows = new Category("Windows", sqlDate, true);
            Category programming = new Category("Programming", sqlDate, true);
            Category gadgets = new Category("Gadgets", sqlDate, true);
            Category events = new Category("Events", sqlDate, true);
            Category webdesign = new Category("Web Design", sqlDate, true);
            Category opensource = new Category("Open Source", sqlDate, true);
            Category computerscience = new Category("Computer Science", sqlDate, true);
            Category security = new Category("Security", sqlDate, true);

            /* Create World Categories */
            Category news = new Category("News", sqlDate, true);
            Category finance = new Category("Finance", sqlDate, true);
            Category politics = new Category("Politics", sqlDate, true);
            Category history = new Category("History", sqlDate, true);
            Category geography = new Category("Geography", sqlDate, true);

            /* Create Science Cateogires */
            Category environment = new Category("Environment", sqlDate, true);
            Category space = new Category("Space", sqlDate, true);
            Category macro = new Category("Macro Domain", sqlDate, true);
            Category micro = new Category("Micro Domain", sqlDate, true);

            /* Create LifeStyle Categories */
            Category artsculture = new Category("Arts & Culture", sqlDate, true);
            Category educational = new Category("Educational", sqlDate, true);
            Category food = new Category("Food", sqlDate, true);
            Category health = new Category("Health", sqlDate, true);
            Category travel = new Category("Travel", sqlDate, true);

            /* Create Entertainment Categories */
            Category music = new Category("Music", sqlDate, true);
            Category movies = new Category("Movies", sqlDate, true);
            Category television = new Category("Television", sqlDate, true);
            Category comics = new Category("Comics", sqlDate, true);

            /* Create Sports Categories */
            Category olympics = new Category("Olympics", sqlDate, true);
            Category soccer = new Category("Soccer", sqlDate, true);
            Category cricket = new Category("Cricket", sqlDate, true);
            Category athletics = new Category("Athletics", sqlDate, true);

            /* Create Offbeat Categories */
            Category comedy = new Category("Comedy", sqlDate, true);
            Category pets = new Category("Pets", sqlDate, true);

            /* Set Parent */
            linux.setParentCategory(technology);
            osx.setParentCategory(technology);
            windows.setParentCategory(technology);
            programming.setParentCategory(technology);
            gadgets.setParentCategory(technology);
            events.setParentCategory(technology);
            webdesign.setParentCategory(technology);
            opensource.setParentCategory(technology);
            computerscience.setParentCategory(technology);
            security.setParentCategory(technology);
            news.setParentCategory(world);
            finance.setParentCategory(world);
            politics.setParentCategory(world);
            history.setParentCategory(world);
            geography.setParentCategory(world);
            environment.setParentCategory(science);
            space.setParentCategory(science);
            macro.setParentCategory(science);
            micro.setParentCategory(science);
            artsculture.setParentCategory(lifestyle);
            educational.setParentCategory(lifestyle);
            food.setParentCategory(lifestyle);
            health.setParentCategory(lifestyle);
            travel.setParentCategory(lifestyle);
            movies.setParentCategory(entertainment);
            music.setParentCategory(entertainment);
            television.setParentCategory(entertainment);
            comics.setParentCategory(entertainment);
            olympics.setParentCategory(sports);
            soccer.setParentCategory(sports);
            cricket.setParentCategory(sports);
            athletics.setParentCategory(sports);
            comedy.setParentCategory(offbeat);
            pets.setParentCategory(offbeat);

            /* Map to Database */
            categoryRemote.create(linux);
            categoryRemote.create(osx);
            categoryRemote.create(windows);
            categoryRemote.create(programming);
            categoryRemote.create(gadgets);
            categoryRemote.create(events);
            categoryRemote.create(webdesign);
            categoryRemote.create(opensource);
            categoryRemote.create(computerscience);
            categoryRemote.create(security);

            categoryRemote.create(news);
            categoryRemote.create(finance);
            categoryRemote.create(politics);
            categoryRemote.create(history);
            categoryRemote.create(geography);

            categoryRemote.create(environment);
            categoryRemote.create(space);
            categoryRemote.create(macro);
            categoryRemote.create(micro);

            categoryRemote.create(artsculture);
            categoryRemote.create(educational);
            categoryRemote.create(food);
            categoryRemote.create(health);
            categoryRemote.create(travel);

            categoryRemote.create(movies);
            categoryRemote.create(music);
            categoryRemote.create(television);
            categoryRemote.create(comics);

            categoryRemote.create(olympics);
            categoryRemote.create(soccer);
            categoryRemote.create(cricket);
            categoryRemote.create(athletics);

            categoryRemote.create(comedy);
            categoryRemote.create(pets);

            /* Echo To Server Log */
            System.out.println("Category Table Created!");
        }
    }

    private void getvisibilityObjects() throws NamingException {
         // = (VisibilityValidationFacadeRemote) EJBUtility.lookup("VisibilityValidationFacade");

        visibilityrecordSet = vvfr.findAll();
        /* Iterate the Visibility Recordset */
        Iterator<VisibilityValidation> iterator = visibilityrecordSet.iterator();
        while (iterator.hasNext()) {
            VisibilityValidation record = iterator.next();
            if (record.getVisibilityName().equals("everyone")) {
                everyoneOBJ = record;
            } else {
                if (record.getVisibilityName().equals("friends")) {
                    friendsOBJ = record;
                } else {
                    if (record.getVisibilityName().equals("me")) {
                        meOBJ = record;
                    }
                }
            }
        }
    }
}
