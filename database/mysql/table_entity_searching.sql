CREATE TABLE E_SEARCHING (
	SEARCHING_ID		INTEGER			NOT NULL PRIMARY KEY AUTO_INCREMENT,
	DESCRIPTION			VARCHAR(200)	NOT NULL,
	USER_ID				VARCHAR(100)	NOT NULL,
	SEARCH_TYPE_CODE	SMALLINT		NOT NULL,
	CREATE_DATE			TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	ACTIVE 				CHAR(1)			NOT NULL CHECK (ACTIVE IN ('Y','N')),
	
	CONSTRAINT FK_E_SEARCHING_USER_ID FOREIGN KEY (USER_ID) REFERENCES E_USER(USER_ID)
);

CREATE TABLE ER_SEACHING_MAP_CONTENT (
	SEARCHING_ID		INTEGER			NOT NULL,
	CONTENT_ID			INTEGER			NOT NULL,
	CREATE_DATE			TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	SCORE_HIT			FLOAT			,
	CONSTRAINT PK_SEACHING_MAP_CONTENT				PRIMARY KEY(SEARCHING_ID, CONTENT_ID),
	CONSTRAINT FK_SEACHING_MAP_CONTENT_SEARCHING_ID FOREIGN KEY(SEARCHING_ID) REFERENCES E_SEARCHING(SEARCHING_ID),
	CONSTRAINT FK_SEACHING_MAP_CONTENT_CONTENT_ID	FOREIGN KEY(CONTENT_ID) REFERENCES E_CONTENT(CONTENT_ID)
);

CREATE TABLE ER_SEARCH_TYPE_MAP_WEB_TYPE (
	SEARCH_TYPE_CODE	SMALLINT		NOT NULL,
	WEB_TYPE_CODE		SMALLINT		NOT NULL,
	CONSTRAINT PK_ER_SEARCH_TYPE_MAP_WEB_TYPE				PRIMARY KEY(SEARCH_TYPE_CODE, WEB_TYPE_CODE),
	CONSTRAINT FK_SEARCH_TYPE_MAP_WEB_TYPE_SEARCH_TYPE_CODE FOREIGN KEY(SEARCH_TYPE_CODE) REFERENCES R_SEARCH_TYPE(CODE),
	CONSTRAINT FK_SEARCH_TYPE_MAP_WEB_TYPE_WEB_TYPE_CODE	FOREIGN KEY(WEB_TYPE_CODE) REFERENCES R_WEB_TYPE(CODE)
);
