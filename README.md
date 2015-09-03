# tamanoir

## Create a new connection

Return new connection.

Method - POST
URL "**/connections**"

**Argument** | **Type/Value** | **Description** |
--- | --- | ---
connectionDescriptor | QueryConnectionDescriptor required | Example: {"url":"jdbc:postgresql://localhost:5432/foodmart","type":"jdbc","properties":{"user":"postgres","password":"postgres"}}
--- | --- | ---
accept | Header required | Option, which determines adding metadata. Example: accept “application/metadata+json” – metadata will be added to responce
--- | --- | ---
include | String | Will include metadata for specified in response. Can be used for multiple nodes. Mutual exclusive with “expand”. Example “/rest/connections?include=public”
--- | --- | ---
expand | String | Will add all children nodes to response. Mutual exclusive with “include”. Example “/rest/connections?expand=public”
--- | --- | ---
recursive | String | Will include metadata for specified node with metadata  for all children nodes. Can be used for multiple nodes. Example “/rest/connections?recursive=public”

Options
* consumes: application/json (default)
* Return Value on Success
	
Typical Return | Values on Failure
--- | ---
200 OK – Connection created. | 404 Not Found –