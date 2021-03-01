package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest{

    @Test
    void isPasswordValidTest(){
            assertFalse(Client.isPasswordValid("Clt1!"));
            assertTrue(Client.isPasswordValid("Client1!"));
            assertTrue(Client.isPasswordValid("Cl1234!"));
            assertFalse(Client.isPasswordValid("ClientC"));
            assertFalse(Client.isPasswordValid("client1!"));
            assertFalse(Client.isPasswordValid("Client!"));
            assertTrue(Client.isPasswordValid("cLient1!"));
            assertFalse(Client.isPasswordValid("Client1"));
            assertFalse(Client.isPasswordValid("CLIENT1!"));
    }

    @Test
    void confirmUserTest(){
        Client c1 = Client()
    }

}