package edu.abhs.hotProperties.utils;

import edu.abhs.hotProperties.entities.User;
import org.springframework.security.core.Authentication;

public record CurrentUserContext(User user, Authentication auth) {}
