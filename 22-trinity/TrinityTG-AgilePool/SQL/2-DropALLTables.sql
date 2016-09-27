USE AgileVendorPool_db;


--IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[FosterParent]') AND type in (N'U')) 
--BEGIN
--	DROP TABLE [dbo].[FosterParentProfile];
--END
--GO

-- *****************************************************
-- Drop TABLES
-- *****************************************************

DROP TABLE [dbo].[FosterParentProfile];
DROP TABLE [dbo].[EmailData];
GO
