Feature: Login System

  In order to have authenticated access to my website
  As an administrator
  I want users to be able to login to the website

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts

  Scenario: Invalid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again
