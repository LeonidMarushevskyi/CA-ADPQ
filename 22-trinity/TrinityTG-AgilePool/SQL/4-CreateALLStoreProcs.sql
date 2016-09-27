USE AgileVendorPool_db;
GO


-- *****************************************************
CREATE PROCEDURE [dbo].[FosterParentGet]
@pEmail  NVARCHAR (255)
AS
BEGIN
	
	IF (ISNULL(@pEmail, '')  = '')
	begin
		select * from [FosterParentProfile] 
		order by Email asc
	end
	else
	begin
		select * from [FosterParentProfile] 
		where  LOWER(Email)=LOWER(@pEmail) 
		order by Email asc
	end
	

END
GO

-- *****************************************************
CREATE PROCEDURE [dbo].[FosterParentInsert]
	@pEmail  NVARCHAR (255),
    @pLastName  NVARCHAR (35),
    @pFirstName NVARCHAR (35),
	@pAddress1  NVARCHAR (100),
	@pAddress2  NVARCHAR (100) = null,
	@pCity  NVARCHAR (35),
	@pState  NVARCHAR (2),
	@pZipCode  NVARCHAR (10),
	@pCountry  NVARCHAR (70)
AS
BEGIN
begin try
	SET NOCOUNT ON
		
	insert into [dbo].[FosterParentProfile] 
		(Email, LastName, FirstName, Address1, Address2, City, [State], ZipCode, Country)
	values
		(@pEmail, @pLastName, @pFirstName, @pAddress1, @pAddress2, @pCity, @pState, @pZipCode, @pCountry )

	select scope_identity()

end try
begin catch
	declare @ErrorMessage nvarchar(4000)
	declare @ErrorSeverity int
	declare @ErrorState int
		
	set @ErrorMessage = error_message()
	set @ErrorSeverity = error_severity()
	set @ErrorState = error_state()
	
	if @@TRANCOUNT > 0         
			rollback tran
	
	RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState)
end catch	

END
GO


-- *****************************************************
CREATE PROCEDURE [dbo].[FosterParentUpdate]
	@pFosterParentId INT ,
	@pEmail  NVARCHAR (255),
    @pLastName  NVARCHAR (35),
    @pFirstName NVARCHAR (35),
	@pAddress1  NVARCHAR (100),
	@pAddress2  NVARCHAR (100) = null,
	@pCity  NVARCHAR (35),
	@pState  NVARCHAR (2),
	@pZipCode  NVARCHAR (10),
	@pCountry  NVARCHAR (70) 

AS
BEGIN
begin try

	UPDATE [dbo].[FosterParentProfile] 
		SET 
			Email = @pEmail ,
			LastName = @pLastName, 
			FirstName = @pFirstName,
			Address1 = @pAddress1,  
			Address2 = @pAddress2, 
			City = @pCity ,
			[State] = @pState ,
			ZipCode = @pZipCode ,
			Country = @pCountry  
		WHERE FosterParentId = @pFosterParentId

end try
begin catch
	declare @ErrorMessage nvarchar(4000)
	declare @ErrorSeverity int
	declare @ErrorState int
		
	set @ErrorMessage = error_message()
	set @ErrorSeverity = error_severity()
	set @ErrorState = error_state()
	
	if @@TRANCOUNT > 0         
			rollback tran
	
	RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState)
end catch	

END
GO

-- *****************************************************
CREATE PROCEDURE [dbo].[FosterParentDelete]
	@pFosterParentId INT
AS
BEGIN
begin try

	delete from [dbo].[FosterParentProfile]  where FosterParentId = @pFosterParentId 

end try
begin catch
	declare @ErrorMessage nvarchar(4000)
	declare @ErrorSeverity int
	declare @ErrorState int
		
	set @ErrorMessage = error_message()
	set @ErrorSeverity = error_severity()
	set @ErrorState = error_state()
	
	if @@TRANCOUNT > 0         
			rollback tran
	
	RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState)
end catch	

END
GO



-- *****************************************************
-- *****************************************************

-- Email area

-- *****************************************************
-- *****************************************************

--[EmailId]  INT IDENTITY (1, 1) NOT NULL, 
--[EmailFrom]  NVARCHAR (255) NOT NULL,
--[EmailTo]  NVARCHAR (255) NOT NULL,
--[EmailSubject]  NVARCHAR (255) NOT NULL,
--[EmailBody]  nvarchar(MAX) NOT NULL,
--[EmailDate]  datetime2 NOT NULL,


-- *****************************************************
CREATE PROCEDURE [dbo].[EmailGet]
@pEmail  NVARCHAR (255)
AS
BEGIN
	
	IF (ISNULL(@pEmail, '')  = '')
	begin
		select * from [EmailData] 
		order by EmailDate desc
	end
	else
	begin
		select * from [EmailData] 
		where ( (LOWER(EmailFrom)=LOWER(@pEmail) ) or (LOWER(EmailTo)=LOWER(@pEmail) ) )
		order by EmailDate desc
	end
	

END
GO
	
-- *****************************************************
CREATE PROCEDURE [dbo].[EmailInsert]
	@pEmailFrom  NVARCHAR (255),
	@pEmailTo  NVARCHAR (255),
	@pEmailSubject  NVARCHAR (255),
	@EmailBody  nvarchar(MAX)
	--[EmailDate]  datetime2 NOT NULL,
AS
BEGIN
begin try
	SET NOCOUNT ON
		
	insert into [dbo].[EmailData] 
		(EmailFrom, EmailTo, EmailSubject, EmailBody, EmailDate)
	values
		(@pEmailFrom, @pEmailTo, @pEmailSubject, @EmailBody, GETDATE())

	select scope_identity()

end try
begin catch
	declare @ErrorMessage nvarchar(4000)
	declare @ErrorSeverity int
	declare @ErrorState int
		
	set @ErrorMessage = error_message()
	set @ErrorSeverity = error_severity()
	set @ErrorState = error_state()
	
	if @@TRANCOUNT > 0         
			rollback tran
	
	RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState)
end catch	

END
GO

-- *****************************************************
CREATE PROCEDURE [dbo].[EmailDelete]
	@pEmailId INT
AS
BEGIN
begin try

	delete from [dbo].[EmailData]  where EmailId = @pEmailId 

end try
begin catch
	declare @ErrorMessage nvarchar(4000)
	declare @ErrorSeverity int
	declare @ErrorState int
		
	set @ErrorMessage = error_message()
	set @ErrorSeverity = error_severity()
	set @ErrorState = error_state()
	
	if @@TRANCOUNT > 0         
			rollback tran
	
	RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState)
end catch	

END
GO

