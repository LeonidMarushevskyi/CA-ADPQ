USE AgileVendorPool_db;
GO



-- *****************************************************
CREATE TABLE [dbo].[FosterParentProfile] (
    [FosterParentId]  INT IDENTITY (1, 1) NOT NULL,
	[Email]  NVARCHAR (255) NOT NULL,
    [LastName]  NVARCHAR (35) NOT NULL,
    [FirstName] NVARCHAR (35) NOT NULL,
	[Address1]  NVARCHAR (100) NOT NULL,
	[Address2]  NVARCHAR (100) NULL,
	[City]  NVARCHAR (35) NOT NULL,
	[State]  NVARCHAR (2) NOT NULL,
	[ZipCode]  NVARCHAR (10) NOT NULL,
	[Country]  NVARCHAR (70) NOT NULL,

    PRIMARY KEY CLUSTERED ([FosterParentId] ASC),
	CONSTRAINT UQ_FosterParentProfile_Email UNIQUE(Email) 
);
GO


-- *****************************************************
CREATE TABLE [dbo].[EmailData] (
    [EmailId]  INT IDENTITY (1, 1) NOT NULL,
	[EmailFrom]  NVARCHAR (255) NOT NULL,
	[EmailTo]  NVARCHAR (255) NOT NULL,
	[EmailSubject]  NVARCHAR (255) NOT NULL,
	[EmailBody]  nvarchar(MAX) NOT NULL,
	[EmailDate]  datetime2 NOT NULL,
	
    PRIMARY KEY CLUSTERED ([EmailId] ASC)
);
GO