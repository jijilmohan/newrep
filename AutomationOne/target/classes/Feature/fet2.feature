Feature: Feature-sample1

Scenario: scenario description
	Given Test Begins for "Practice form-pass scenario"
	

Scenario: Page vheader 
    Given I am in "FormsPageHeaderPass" with "Automation Practice Form" Header
    When I Click on "FormsMaleCheckbox" Button
    Then I Verify "FormsPagePersonalInfoText" with "PERSONAL INFORMATION" Value
