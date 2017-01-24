# Features
Main
- When loading the page: get switches statuses and connect to switch updates
- User clicks switch -> PUT /switch/{id:1, state: 'OFF'}
- User gets switches' status via WebSockets:
	* When ever a user clicks a switch, it's broadcasted to all e.g. {id:1, state: 'OFF'}

Optional
- User gets other users' actions via WebSockets every action: [{action: "1 to ON"}, ...]
