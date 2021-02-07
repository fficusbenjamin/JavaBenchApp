package com.example.javapj.Tests;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BcryptHashing {
    public static void main()
    {
        /*String  password = "Napier2021";
        String generatedSecuredPasswordHash = BCrypt.Hasher(originalPassword, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);*/

        String  password = "Napier2021";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        System.out.println(result);
    }
}
