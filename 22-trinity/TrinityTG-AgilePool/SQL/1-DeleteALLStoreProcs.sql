USE AgileVendorPool_db;

-- *****************************************************
-- Drop STORE PROCEDUREs 
-- *****************************************************

--IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DeleteParent]') AND type in (N'P', N'PC'))
--BEGIN
--	DROP PROCEDURE [dbo].[DeleteParent];
--END
--GO


DROP PROCEDURE [dbo].[FosterParentGet];
DROP PROCEDURE [dbo].[FosterParentInsert];
DROP PROCEDURE [dbo].[FosterParentUpdate];
DROP PROCEDURE [dbo].[FosterParentDelete];


DROP PROCEDURE [dbo].[EmailGet];
DROP PROCEDURE [dbo].[EmailInsert];
DROP PROCEDURE [dbo].[EmailDelete];

