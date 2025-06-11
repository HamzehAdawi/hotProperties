package edu.abhs.hotProperties.initializer;

import edu.abhs.hotProperties.entities.*;
import edu.abhs.hotProperties.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.List;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyImageRepository propertyImageRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavoriteRepository favoriteRepository;
    private final MessagesRepository messagesRepository;


    @Autowired
    public DataInitializer(UserRepository userRepository, PropertyRepository propertyRepository, PropertyImageRepository propertyImageRepository,
                           FavoriteRepository favoriteRepository, PasswordEncoder passwordEncoder,  MessagesRepository messagesRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.propertyImageRepository = propertyImageRepository;
        this.favoriteRepository = favoriteRepository;
        this.passwordEncoder = passwordEncoder;
        this.messagesRepository = messagesRepository;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0 && propertyRepository.count() == 0 && propertyImageRepository.count() == 0 && favoriteRepository.count() == 0 && messagesRepository.count() ==0) {

            User u1 = new User("Buyer",
                    "USER",
                    passwordEncoder.encode("buyer123"),
                    "buyer@email.com",
                    User.Role.BUYER);

            User u2 = new User("Admin",
                    "USER",
                    passwordEncoder.encode("admin123"),
                    "admin@email.com",
                    User.Role.ADMIN);

            User u3 = new User("Agent",
                    "USER",
                    passwordEncoder.encode("agent123"),
                    "agent@email.com",
                    User.Role.AGENT);

            userRepository.saveAll(List.of(u1, u2, u3));
            System.out.println("Initial users, inserted successfully");

            Property p1 = new Property("3818 N Christiana Ave",
                    1025000,
                    "Experience luxury living in this beautifully redesigned single-family home, where expert craftsmanship and meticulous attention to detail shine throughout. The sun-drenched open-concept main level boasts a seamless flow between the living and dining areas, complemented by a cozy family room featuring a gas fireplace and elegant doors leading to the rear patio & spacious back yard. The stunning chef's kitchen is a true showpiece, featuring custom cabinetry, a designer tile backsplash, high-end stainless steel appliances, and an expansive center island-perfect for entertaining. Upstairs, the spacious primary suite offers a spa-like retreat with a luxurious ensuite featuring a frameless glass shower and dual vanities. Step through the patio doors to enjoy a large private balcony, perfect for morning coffee and fresh air. Two additional bedrooms, a stylish full bath, and generous storage complete the second floor, including a bright and airy bedroom that features a beautiful terrace overlooking the tree-lined street-an inviting space to relax and enjoy the neighborhood views. The sunlit third level is the epitome of this home, offering a bright and inviting alternative to a traditional basement. This exceptional space features a fourth bedroom, another full guest bath, a dedicated laundry area, and a versatile, spacious, recreation room complete with a wet bar, wine fridge, and a walk-out terrace. Flooded with natural light, it provides the perfect blend of comfort and functionality for both relaxing and entertaining. Outside, the professionally landscaped sizable yard offers a picturesque setting, while the two-car garage adds convenience. Situated in a sought-after neighborhood, this stunning home boasts incredible curb appeal and is truly a must-see! All of the outdoor spaces, including the charming front porch, balconies off of multiple rooms, rear patio, and beautifully designed yard, are unique highlights that enhance this home's appeal!",
                    "Chicago IL 60618",
                    3600);

            Property p2 = new Property("3423 N Kedzie Ave",
                    899000,
                    "Oversized all-brick single-family home in East Avondale between Logan Square and Irving Park.  Approximately 4600 sq. ft.  6 large bedrooms and 3.5 baths.  Front entrance foyer and great mudroom in the rear.  High ceilings.  Large eat-in kitchen with tons of cabinets and big walk-in pantry.  The top floor has 3 big bedrooms including an enormous master suite with his and her walk-in closets, a large laundry room, and skylights.  The lower level has good natural light with 9-foot ceilings and a true in-law/2nd unit suite with another 3 huge bedrooms/bath/living room/full kitchen and a 2nd laundry room!  2 zones of heating and cooling.  Interior/exterior drain tiles.  Professionally landscaped and gated front and back yards with wonderful perennial plants.  2.5 car garage with room for storage.  Terrific roof deck with trex decking and pergola!  Great location only 3 blocks to the Belmont Blue Line stop.  Easy access to 90/94.  Near great public and private schools, 312 RiverRun trail system, parks, taprooms, coffee shops, restaurants, climbing gym, Chicago river boathouses, and more!  The house has been very well maintained and is move-in ready!",
                    "Chicago IL 60618",
                    4600);

            Property p3 = new Property("1837 N Fremont St",
                    3795000,
                    "Welcome to this architectural masterpiece, nestled in the heart of Lincoln Park on the serene, tree-lined Fremont Street. This spacious, 5-bedroom contemporary haven has been thoughtfully remodeled by renowned designer Donna Mondi, seamlessly blending elegance, modern design, and meticulous attention to detail to create an unparalleled living experience.  From the moment you step inside, you're welcomed by soaring ceilings and an abundance of natural light pouring through two-story front windows, complete with electric-controlled shades that offer the perfect balance of privacy and illumination. The living room radiates warmth with its inviting fireplace, setting the tone for a space that effortlessly flows into the state-of-the-art kitchen.  In this culinary haven, the stunning custom Arabescato Corchia marble island takes center stage, complemented by a handcrafted vintage brass hood and an Italian-inspired water line for cookware. Premium Miele appliances, including an integrated coffee machine, enhance the space, while hand-polished lacquer cabinets exude sophistication. This kitchen is the perfect fusion of beauty and functionality, ideal for both casual gatherings and elegant entertaining.  The dining area, complete with its own fireplace, offers a serene setting to host friends and family. The NanaWall glass door system opens to a tranquil Japanese garden fountain, merging indoor elegance with outdoor serenity. The main level also features a beautifully redesigned powder bath with custom Venetian plaster and a sleek steel and frosted glass sliding door. On the second level, you'll find three generously sized bedrooms, one currently used as a den, along with a spacious bathroom that includes a dual vanity and a walk-in shower. This level also offers a convenient laundry room.  The third level is dedicated entirely to a tranquil primary suite, offering incredible natural light and treetop views of Fremont Street. The spacious walk-in closet, outfitted with custom shelving and drawers, provides ample storage, while a second washer/dryer adds ultimate convenience. The spa-like ensuite bathroom features floating dual vanities and a steam shower, creating a sanctuary of relaxation. Step outside to your private rooftop terrace complete with a custom stainless steel chef's kitchen perfect for outdoor dining. Adorned with vibrant perennials and offering panoramic views of the city skyline, this space also has the ability to accommodate solar panels. A convenient dumbwaiter connects this rooftop paradise to the main kitchen below, enhancing both luxury and functionality.  The lower level offers an expansive family room, a climate-controlled wine cellar, and a private guest suite, ensuring comfort and elegance for every family member or guest. Throughout the home, wide plank walnut flooring and heated floors add warmth and sophistication. The Savant smart system seamlessly controls lighting, climate across seven zones, and entertainment, elevating your home life experience to the next level. Completing this exceptional property is a large, detached 2-car garage with radiant heated floors and an additional rooftop deck, perfect for enjoying the outdoors or gardening. A majestic maple tree graces the front, enhancing the home's curb appeal. Situated just steps from vibrant dining, shopping, and entertainment, this contemporary home on Fremont Street embodies luxury and comfort living at its best.",
                    "Chicago IL 60614",
                    4662);

            Property p4 = new Property("2818 W Wellington Ave",
                    899000,
                    "Experience Unparalleled Luxury! Welcome to this stunning, one-of-a-kind luxury home, where elegance meets modern comfort. Nestled in a prestigious neighborhood, this 3-bedroom, 3.5-bathroom masterpiece boasts breathtaking architecture, high-end finishes, and exceptional craftsmanship. Key Features: Grand entrance with high ceilings & beautiful chandelier. Expansive open concept living space with glass doors. Gourmet chef's kitchen with state-of-the-art appliances, custom cabinetry and more. Then step into the beautiful backyard, a perfect blend of luxury, relaxation, and entertainment. Whether you're hosting gatherings, enjoying a quiet evening, or creating lasting memories, this outdoor has it all! Prime Location: Located in Avondale Neighborhood, this home offers exclusivity, privacy, and convenience-just minutes away from the express ways I90/94, public transportation, great schools, fine dining, and entertainment. Schedule your private showing today and be impressed.",
                    "Chicago IL 60618",
                    3000);

            Property p5 = new Property("3454 W Potomac Ave",
                    959000,
                    "Modern 6 bed, 4.1 bath new construction home on an oversized 30' corner lot! Best price/sf of a new house in the area!  Extra wide floor plan with great natural light. Contemporary black and white color palette with natural wood accents throughout. Open concept main level perfect for entertaining. Spacious combined living/dining area with built-in banquet. Chef's kitchen with striking floor to ceiling black cabinetry and quartz counters, Cafe appliances, large center island with waterfall counter and bar seating, and walk-in pantry. Adjoining family room with patio doors to back deck. Sun-filled primary suite with huge built-out WIC and private bath featuring a frameless glass shower, freestanding tub, and dual sinks. Three additional bedrooms, one with ensuite bath, the other two with shared jack and Jill bath + laundry complete the upper level. Finished lower level includes a spacious recreation room with full wet bar, two bedrooms, full bath, second laundry, extra storage space, and utilities. Great outdoor space! Fully enclosed yard with back deck and detached two-car garage. Conveniently located near restaurants, entertainment, shopping, public transportation and 200-acre Humboldt Park!",
                    "Chicago IL 60651",
                    4098);

            Property p6 = new Property("461 W Melrose St",
                    3300000,
                    "East Lakeview is the setting for this gorgeous contemporary masterpiece. The home is comprised of four levels of living space. The spaces are all accessible by an elevator that can transport you from the basement to the roof and all levels in between. There is a beautiful and spacious living room that combines and flows with the large dining room. The kitchen is  a chef's dream and perfect for those who like to cook, entertain or simply enjoy such wide open space. The kitchen has an island with a beautiful custom made marble countertop, high end appliances, pantry, professional range hood, and custom cabinets. The kitchen has various ceiling heights including an area where it reaches upwards of twenty feet, giving the area sense of unmatched volume. The first floor is complimented by an open family room. The rest of the home includes six ample bedrooms, three full baths and two half baths. The generous sized private primary suite has a large walk in closet leading to one of several outside decks. It is complimented by a complete bathroom including all modern amenities. The  full finished basement has high ceilings and perfectly equipped for your relaxation. Outside is a beautiful fully fenced back yard just waiting for your outside enjoyment. The sellers have made many improvements which include- newer kitchen, new flooring, new windows, newer mechanicals, new light fixtures, custom closets, new drainage and tile in the backyard, heated gutters and downspouts, and high end appliances. All this plus, RM6 zoning, highly coveted parking spaces, and within a half block to Lake Shore drive and the incomparable Lake Michigan. This is truly a gem. Hurry !!!",
                    "Chicago IL 60657",
                    5400);

            Property p7 = new Property("1741 N Mozart St",
                    849000,
                    "Reimagined Logan Square single-family home that's been carefully curated by the owner/designer. Three bedrooms up, with a large primary suite, full finished lower level with bedroom, & family room. The living floor has high ceilings, oversized windows, and sliders to the new back deck, allowing for indoor/outdoor living. Large open kitchen with updated quartz countertops, lighting ,and stainless appliance package. Huge dining room with design-forward lighting and fireplace. Charming front porch, 2.5 car garage and a few steps from the 606. Open this Saturday March 29th from 2p to 5pm and Sunday March 30th from 11a to 2p. See additional info for FAQ sheet",
                    "Chicago IL 60647",
                    2631);

            Property p8 = new Property("2317 W Ohio St",
                    949000,
                    "Fully gut-rehabbed in 2022, this 4 bedroom, 3.1 bath, 3000 SQFT single family home ideally located in Chicago's Ukrainian Village neighborhood. Flooded with natural light, the main level's open floor plan flows seamlessly and creates the ideal space for both entertaining and everyday living. The eat-in kitchen features an expansive center island with breakfast bar, Bosch appliances, and copious cabinet and counter space. A family room off the kitchen leads to a back deck and the backyard, ideal for grilling, relaxing, and dining al fresco. Upstairs on the second floor, three bedrooms, 2 full baths, and a laundry room are conveniently located on the same level. The lower level has a recreation room, a 4th bedroom, and a full bath. An all brick 2-car garage with roof deck was added to the property in 2022 and provides an urban oasis for relaxation and entertainment purposes. Ideally located near all Ukrainian Village has to offer and a short ride to the vibrant West Loop area. Close to public transportation and one block from Mitchell School.",
                    "Chicago IL 60612",
                    3000);

            Property p9 = new Property("1701 N Dayton St",
                    4750000,
                    "Stunning LG custom-built single-family home on an extra wide 36.5' lot on a quiet stretch of Dayton in the heart of Lincoln Park. The classic red brick and limestone facade with bluestone steps to a carved walnut door welcomes you into this sweeping home that offers relaxed elegance with exceptional millwork, timeless finishes, and a thoughtful floor plan.    The elevated entryway features an oval tray ceiling and a symmetrical inlaid floor medallion inviting you into the gracious living room with a marble fireplace and adjoining ample dining room and butler's wet bar complete with a polished stainless steel sink, lined silver drawers, and a spacious walk-in pantry. The main gathering space of the first floor is the gourmet kitchen, boasting an angled coffered ceiling, double islands with Shaws' Farmhouse sinks, a mirrored Sub Zero refrigerator, and top-of-the-line Wolf appliances, including a wall oven, warming drawer, and side-by-side double oven with a griddle. Built-ins abound, plus a breakfast banquette that offers ample space for dining. The adjacent family room features a coffered ceiling and a grand oversized marble fireplace, flanked by paneled bookcases and more built-ins for the reading area and workstation. French doors with seeded leaded glass window transoms lead to a bluestone patio with a seating area and built-in DCS grill, providing a seamless transition to outdoor entertaining on the full-width 640 sqft Trex deck with a pergola. and a generously sized family room featuring an oversized marble fireplace and reading area. The first-floor features hickory, hand-scraped, and beveled hardwood floors, extensive millwork, crown molding, and 8 1/2 baseboard trim",
                    "Chicago IL 60614",
                    8000);

            Property p10 = new Property("334 N Jefferson St UNIT D",
                    925000,
                    "This rare corner townhome in Kinzie Station offers 3 bedrooms and 3 baths, showcasing a renovated kitchen and an open-concept design. With a bright southeast exposure, natural light pours into the home, complemented by multiple private outdoor spaces with stunning city views.    The main level features a marble entryway and a versatile bedroom with an ensuite bath-ideal for a guest room, home office, or playroom. Upstairs, the primary suite boasts a walk-in closet and a beautifully updated bathroom with dual sinks. A second bedroom, a refreshed bathroom, and a laundry area complete this floor.    The sun-drenched third level highlights a spacious living room with a gas fireplace, elegant Brazilian Cherry hardwood floors, a dedicated dining area, and a balcony. The sleek white kitchen is equipped with a Viking stove, Sub-Zero refrigerator, expansive countertops, and a large breakfast bar.    On the fourth floor, a generous family room with a wet bar opens to an enormous private roof deck, complete with a gas line for grilling and built-in surround sound-perfect for entertaining or unwinding. Flexible spaces throughout the home allow for an office or a potential fourth bedroom. The property also includes a fenced yard and access to a private association parking lot, ideal for outdoor activities.    Residents enjoy access to amenities in the adjacent condo building, including a newly updated gym, security guard service, and secure package delivery. With FOUR parking spaces (garage, driveway, and two in the adjacent lot), this home offers both convenience and luxury. Experience peaceful, residential living just moments from the Loop, River North, and Fulton Market, with easy access to dining, shopping, parks, and the 90/94 expressway.",
                    "Chicago IL 60661",
                    2600);

            Property p11 = new Property("1249 S Plymouth Ct",
                    1200000,
                    "Welcome home to your urban oasis in this rarely available single family home in Chicago's South Loop! Located in Dearborn Park II, on a quiet tree-lined street directly across from lovely Mary Richardson Jones Park and highly rated South Loop Elementary, this 3000+ sqft home has three levels & offers three bedrooms and three and a half newly renovated bathrooms and so much more. This bright and sunny home features hardwood flooring thru-out the main levels and a carpeted cozy basement level. Enjoy sunny western & park views from the spacious and open living/dining room combo. Try your culinary skills in the large, renovated kitchen featuring high-end Viking & Thermador appliances, granite countertops & custom cabinetry. Grow your herbs year round in the kitchen's charming all-glass garden window. Step down from the kitchen into the bright & spacious family room which features high ceilings, built-in oak shelving and marble surrounded wood-burning fireplace. With various floor plans on the block, the top level of this home features THREE spacious bedrooms, two full baths and a full size side-by-side washer/dryer. The bright and spacious primary bedroom has oak flooring, two large closets and overlooks the rear patio with skyline views! The lower level can be converted to a 4th bedroom or in-law arrangement and has a new full bathroom, an extra large refinished office area which easily could be converted to suit your needs and a huge additional storage room. The roof is less than 10yrs old! The two-car detached parking garage includes plenty of storage space and leads to a gated interior alleyway shared by the association. Steps from Grant Park, Michigan Avenue shops, Museum Campus, lakefront trail and beaches, restaurants, Jewel, Target, Trader Joe's and convenient access to Loop, Lake Shore Drive, U of C shuttle, expressways and public transit.",
                    "Chicago IL 60605",
                    3000);

            Property p12 = new Property("2779 N Kenmore Ave",
                    1300000,
                    "Unicorn alert! This rare, beautifully renovated Victorian in the heart of Lincoln Park seamlessly blends historic craftsmanship with contemporary elegance, offering a truly one-of-a-kind living experience. With 4 bedrooms and 3 levels of living space above grade, this home is truly a rare find. No detail was overlooked in this full gut renovation, preserving stunning original features like stained glass windows, vintage banisters, and intricate tile work while introducing modern comforts. The main level offers the perfect balance between an open, flowing layout and defined spaces, featuring custom built-ins, a fireplace, 6 engineered white oak floors",
                    "Chicago IL 60614",
                    2532);

            Property p13 = new Property("4425 N Winchester Ave",
                    1125000,
                    "Lovely Victorian home on large lot in Ravenswood. This very wide home offers formal foyer, living and dining rooms. 10' ceilings with over sized windows maximizes the light of the house. 2 extra rooms that could be offices/homework room/game room/bedroom both with closets. Full bath with walk in shower plus storage. Amazing kitchen/ great room with breakfast area and built in pantry. Gas fireplace with blower. Sleek maple kitchen with Verde Jaco Italian granite. Upstairs are 3 good size bedrooms with maples and new carpeted floors. Lots of nooks and crannies to discover. Lower level offers newly painted floors and walls making a great space for exercise/mud room/ storage. Plus the original grandfathers workshop with tool bench. Side entrance easy access. And then there is the oversized landscaped yard with wisteria bush, sunset maple, and ornamental pear trees that create the most wonderful setting. Back deck with pergola for morning coffee or evening dinners. Huge front porch offers neighborly hellos on this wonderful block. New painted interior, new carpet, and new light fixtures are some of the recent upgrades. Hardie board siding. Two car garage with side parking pad. Lycee just 1/2 block away. Montrose el stop 2 blocks. Plus all the shopping and restaurants that Montrose has to offer. This home has so much space both inside and out. Don't miss the chance to call this one home.",
                    "Chicago IL 60640",
                    3000);

            Property p14 = new Property("4511 N Saint Louis Ave",
                    889000,
                    "A perfect blend of traditional charm and modern elegance, this fully rebuilt contemporary home sits on a generous 38' wide lot, featuring a wrap-around porch and rear deck. The original structure was completely transformed with a brand-new second story and a 13' rear addition, creating a total of 3,213 sq. ft. of finished living space.    The open-concept first floor is designed for entertaining, boasting a half bath, a striking fireplace feature wall, and seamless flow between indoor and outdoor spaces. The spacious kitchen centers around a large island with clear sightlines to both the front and back patio doors, leading to a covered porch with tongue-and-groove ceilings and recessed lighting.    Upstairs, you'll find three bedrooms, including a luxurious primary suite with a soaking tub, separate shower, and walk-in closet. Two additional bedrooms share a beautifully designed second full bath. A convenient second-floor laundry room is also included.    The fully finished basement offers additional living space with a bonus room, a fourth bedroom or playroom, and a theater room/den with soaring 9' ceilings in the rear addition (with lower ceilings in the remaining basement area). The property also includes a detached two-car garage and an extra parking space beside it.    Wrapped in Hardie Board siding, this home is sits in a vibrant neighborhood surrounded by new construction.",
                    "Chicago IL 60625",
                    3213);

            Property p15 = new Property("401 W Dickens Ave",
                    5995000,
                    "This ultra notable single-family home sits on an extra-wide lot at the corner of Sedgwick and Dickens! The setting of the home allows for open views outside. Every finish is high end with slab marble floors, beautiful hardwood floors, custom woodwork, and high-end built ins. Elevator to all levels, plus powder rooms at each entertaining level (three in total) for added convenience. Totally luxurious & custom finishes throughout. Enjoy beautiful formal spaces unique to the marketplace. The main living level offers large room sizes and access to the outside. Wonderful library/family room which walks out to the yard. Large kitchen and dining with amazingly sunny views. Primary bedroom with a dressing room, huge marble bath plus 3 additional bedroom suites. Glorious top floor atrium sunroom is the crown glory of the home with entire roof deck, fireplace and prime entertaining and living space. Attached 2 car garage plus driveway. Easy walk to Francis Parker and Latin schools, as well as Lincoln Elementary. Please ask about exclusions.",
                    "Chicago IL 60614",
                    7252);

            Property p16 = new Property("339 W Webster Ave UNIT 2B",
                    1225000,
                    "Enjoy the perfect, prime East Lincoln Park location in this wonderfully intimate gated townhome community. This west facing, super sunny townhome lives like a single-family home, with four full floors of living space, a private rooftop deck, and one garage space to top it off! The main living level features beautiful living space, dining, plus an updated kitchen - an entertainer's dream with coffered ceilings and an abundance of windows. The all-white kitchen features Viking, Subzero, and Bosch appliances, along with ample cabinetry, quartz countertops and island seating. The next level up features two generously sized bedrooms that share a well-appointed bath, plus a laundry room. The top level is devoted entirely to the primary suite with a recently updated bathroom featuring double sinks, a walk-in shower, and a skylight for sensational natural light. Not to be missed is the gracious first floor living space with a wet sink/bar and wine fridge, walk out west facing landscaped patio, and an additional full bathroom. The private roof deck has views for days! Steps from Parker, the zoo, lake, amazing dining, plus Lincoln Schools, this is a fantastic opportunity to live in one the best Lincoln Park locales!",
                    "Chicago IL 60614",
                    2400);

            Property p17 = new Property("1541 W Addison St",
                    1200000,
                    "Don't miss this pristine, beautifully rehabbed solid brick home just steps to the highly desirable Southport Corridor. A rare find, boasting 4 bedrooms, 3 1/2 baths and three outdoor spaces, including an amazing fenced backyard. Remarkable features include hardwood flooring, custom stainless kitchen with pantry and designer cabinets, great bathrooms including a custom primary ensuite, a huge lower-level family room, two car garage and new turf and irrigation system in fenced-in backyard. Everything has been redone in this home and don't let the address fool you - its super quiet inside! A jewel in Lakeview, the Southport Corridor thrives with culture, charm, and unique shopping, amazing dining and theatre. Grocery and public transport only a couple of blocks away make this location A+!",
                    "Chicago IL 60613",
                    2869);

            List<Property> properties = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17);
            propertyRepository.saveAll(properties);

            try {

                File mainDir = new File("/F:/finalProject/images/PropertyImages/");
                File[] titleDir = mainDir.listFiles();

                assert titleDir != null;
                for (File imageList : titleDir) {
                    for (Property property : properties) {
                        u3.addProperty(property);
                        property.setUser(u3);
                        if (property.getTitle().equals(imageList.getName())) {
                            File[] allFiles = imageList.listFiles();
                            assert allFiles != null;
                            for(File files : allFiles)
                            {
                                PropertyImage currImage = new PropertyImage(files.getName());
                                property.addPropertyImage(currImage);
                                currImage.setProperty(property);

                                propertyImageRepository.save(currImage);
                            }
                        }
                    }


                }
                userRepository.save(u3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Properties, and property images inserted.");

            Favorite f = new Favorite(u1, p1);

            u1.getFavList().add(f);
            p1.getFavList().add(f);


            favoriteRepository.save(f);
            System.out.println("Initial favorites, inserted successfully");

            Messages messages = new Messages(u1, p1, "Hello, this is a test");

            u1.getMessageList().add(messages);
            p1.getMessageList().add(messages);

            messagesRepository.save(messages);
        }
        else {

            System.out.println("🟡 Users, Properties, and Property Images already exist, skipping initialization.");
        }
    }
}