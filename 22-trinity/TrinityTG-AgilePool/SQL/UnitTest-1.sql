-- *****************************************************
-- Unit Tests
-- *****************************************************

declare @pFosterParentId int;

-- *****************************************************	
-- INSERT Foster Parents
-- *****************************************************	
exec [FosterParentInsert] 'abc-1@yahoo.com', 'LastName1', 'FirstName1', '1 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-2@yahoo.com', 'LastName2', 'FirstName2', '2 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-3@yahoo.com', 'LastName3', 'FirstName3', '3 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-4@yahoo.com', 'LastName4', 'FirstName4', '4 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-5@yahoo.com', 'LastName5', 'FirstName5', '5 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-6@yahoo.com', 'LastName6', 'FirstName6', '6 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-7@yahoo.com', 'LastName7', 'FirstName7', '7 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-8@yahoo.com', 'LastName8', 'FirstName8', '8 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec [FosterParentInsert] 'abc-9@yahoo.com', 'LastName9', 'FirstName9', '9 2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'

-- *****************************************************	
-- READ ALL from  Foster Parent
-- *****************************************************
select * from [FosterParentProfile]

-- *****************************************************	
-- Update Foster Parent
-- *****************************************************
declare @pFosterParentId int;	
set @pFosterParentId =1;
exec FosterParentUpdate @pFosterParentId, 'Abc@Yahoo.com', 'LastName2', 'FirstName1', '2015 J Street', 'Suite 100', 'Sacramento', 'CA', '96000', 'USA'
exec FosterParentGet 'Abc@Yahoo.com'

-- *****************************************************	
-- Delete Foster Parent
-- *****************************************************	
set @pFosterParentId =2;
exec FosterParentDelete 1
exec FosterParentsGet


-- *****************************************************	
-- INSERT Email from Case Worker to Foster Parent
-- *****************************************************	
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 1', '11 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 2', '12 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 3', '13 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 4', '14 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 5', '15 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 6', '16 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 7', '17 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 8', '18 lots of data goes here...'
exec [EmailInsert]  'CaseWorkerEmail@DomainName.com', 'abc-2@yahoo.com', 'Test 9', '19 lots of data goes here...'

-- *****************************************************	
-- INSERT Email from Foster Parent to Case Worker
-- *****************************************************	
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 11', '11 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 12', '12 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 13', '13 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 14', '14 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 14', '15 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 15', '16 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 16', '17 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 17', '18 lots of data goes here...'
exec [EmailInsert]  'abc-2@yahoo.com', 'CaseWorkerEmail@DomainName.com', 'Test 18', '19 lots of data goes here...'


-- *****************************************************	
-- GET ALL Email 
-- *****************************************************
exec [EmailGet] ''

-- *****************************************************	
-- GET Email Where...
-- *****************************************************	
exec [EmailGet] 'abc-@yahoo.com'
exec [EmailGet] 'abc-2@yahoo.com'
exec [EmailGet] 'aBC-2@YAHOO.COM'
exec [EmailGet] 'caseWorkerEmail@DomainName.com'

-- *****************************************************	
-- DELETE Email Where...
-- *****************************************************	
exec [EmailDelete] 1


