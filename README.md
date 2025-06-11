## Hot Properties
Hot properties is a full stack real estate web app included with features such as browsing properties, listing properties, inquiring through messaging, favorties, all with DB persistence through MySQL. 
Users accounts are broken down into 3 roles all of which are responsible for something different. Admin for account creation (agents) via Admin accounts, Agents for property listing, and Buyers for shopping properties. 

## Account Information & Active Username/Password
ADMIN Accounts: 
- email: admin@email.com
- password: admin123
- Able to create Agent users (responsible for listing properties)

AGENT Accounts:
- agent@email.com
- password: agent123
- Responsible for posting listings, responding to buyers messages, adding / deleting properties etc..
- Licensed and can only be created by admins
  
BUYER Accounts:
- Able to search and browse available properteis, contact agents, favortie properties, etc.
- Created by anyone through register page

## Demo

AGENT Demo

https://github.com/user-attachments/assets/8ae20c32-1858-43fa-b00c-6ffd8fbfcf94



https://github.com/user-attachments/assets/cbf875ef-3aa0-4ae1-9cda-cdd9fcf83ef6

BUYER Demo


https://github.com/user-attachments/assets/de603d42-f81c-4550-abce-9ba1e7d0a689



ADMIN Demo


https://github.com/user-attachments/assets/e446cfe7-0f2d-4458-b135-4c7bee3e305d


## Additional Features
(1) Messaging, (2) Favorites, (3) Deleting Properties and Users, (4) Browse Property Filters

## Contribution Summary
- config
  - CustomAccessDeniedHandler.java: Hamzeh     
  - CustomAuthenticationEntryPoint.java: Hamzeh  
  - SecurityConfig.java: Joe and Hamzeh
- controller
  - UserController.java: All
- dtos
  - BadParamaterException: Hamzeh
  - AlreadyExistsException: Hamzeh
  - JwtResponse.java: Joe & Hamzeh
- entities
  - Property.java: Hamzeh & Zach
  - Favorite.java: Zach 
  - PropertyImage.java: Hamzeh 
  - Messages: Hamzeh
  - User.java: ALL
- exceptions
  - NotFoundException.java: Madeleine 
  - UserAlreadyExistsException.java: Madeleine
- initializer
  - DataInitializer.java: Zach & Hamzeh 
- jwt
  - JwtUtil.java: Joe
- repository
  - FavoriteRepository.java: Zach
  - PropertyImageRepository.java: Hamzeh
  - PropertyRepository.java: Hamzeh & Zach
  - UserRepository.java: Hamzeh and Madeleine
  - MessagesRepository: Hamzeh
- service
  - AuthService: Hamzeh
  - AuthServiceImpl.java: Hamzeh, Zach, Joe
  - PropertyServiceImpl: Hamzeh
  - PropertyImageServiceImpl: Hamzeh
  - CustomUserDetailsService.java: Hamzeh
  - CustomUserDetailsServiceImpl.java: Hamzeh & Joe
  - UserService.java: All
  - UserServiceImpl.java: All
  - MessageServices: Hamzeh
  - MessagesServiceImpl: Hamzeh
- utils
  - CurrentUserContext.java: Hamzeh
  - GlobalRateLimiterFilter.java: Hamzeh
  - JwtAuthenticationFilter.java: : Hamzeh
  - JwtSecretGenerator.java: Hamzeh

- HTML 
  - admin_style.css: Madeleine
  - dashboard_style.css: Hamzeh, Zach, Madeleine (support)
  - login.css & HTML: Joe
  - profile_style.css: Hamzeh
  - property_style.css: Zach
  - 
- templates
  - add_agent.html: Madeleine
  - add_properties.html: Hamzeh
  - agentViewMessage.html: Hamzeh
  - all_users.html: Madeleine
  - browse_properties.html: Hamzeh
  - dashboard.html: All
  - edit_profile.html: Hamzeh & Zach
  - edit_property.html: Hamzeh
  - favorites.html: Zach
  - fragments.html: Hamzeh
  - login.html: Joe
  - manage_properties.html: Hamzeh
  - messages.html: Hamzeh
  - messagesBuyer.html: Hamzeh
  - my_profile.html: Hamzeh & Zach
  - property_view.html: Zach
  - register.html: Joe
