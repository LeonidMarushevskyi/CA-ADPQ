Feature: Config
  To be run before all other tests

@prod
	Scenario: Set window size to max
    When I set the window size to width "1080" and height "900"
