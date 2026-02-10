package com.testProject.utils;

public final class Constants {

    public Constants() {
    }

    public static final class Messages {

        public static final String emptyRequestBody = "Empty Request Body";

        public static final String someThingWentWrong = "Something Went Wrong";

        public static final String emptyDeviceType="Empty Device Type";


        public static final String nullOrEmptyEmployeeId ="Null or Empty Employee Id";

        public static final String emptyMobileNumber ="Empty Mobile Number";

        public static final String emptyOTP ="Empty Mobile Number";

        public static final String emptyTripId ="Null or Empty Trip Id";

        public static final String emptyFcmToken ="Empty FCM Token";

        public static final String emptyDeviceId="Empty Device Id";

        public static final String loggedOutMessage="User Logged Out";

        public static final String noUserFound="No User Found";

        public static final String noOtpFound="OTP Not Found";

        public static final String alreadyLoggedIn="Already Logged In";

        public static final String otpExpired="OTP Expired";

        public static final String noActiveUserFound="No Active Employee Found";


        public static final String NOT_AUTHORIZED_TO_CREATE_SITE="Not Authorized to create a Site";
        public static final String NOT_AUTHORIZED_TO_CREATE_EMPLOYEE="Not Authorized to create a Employee";

        public static final String COMPANY_MISMATCH="Company Mismatch";

        public static final String invalidOTP="Invalid OTP";

        public static final String invalidMobileNumber="Invalid Mobile Number";

        public static final String emptyEmailId="Null or Empty Email Id";

        public static final String emptyCompanyName="Null or Empty Company Name";
        public static final String emptyGstNumber="Null or Empty GST Number";

        public static final String error = "An unexpected error occurred";

        public static final String success = "Success";


        public static final String badRequest ="Bad Request";

        public static final String unAuthorized ="Un Authorized -- Invalid Credentials";

        public static final String emptyRequestType ="Empty Expense Request Type";




        public static final String EMPLOYEE_REGISTERED_SUCCESSFULLY="Employee registered successfully.";

        public static final String EMPLOYEE_NOT_FOUND="Employee Not Found";

        public static final String SUPPORT_EMPLOYEE_NOT_FOUND="Support Employee Not Found";

        public static final String NULL_OR_EMPTY_FILE="Null or Empty File";

        public static final String INVALID_FLAG ="Invalid Flag";

        public static final String NULL_OR_EMPTY_CHECK_IN_TIME="Null or Empty Check In Time";

        public static final String NULL_OR_EMPTY_CHECK_OUT_TIME="Null or Empty Check Out Time";


        public static final String emptyPassword="Null or Empty Password Value";

        public static final String invalidPassword="Invalid Password";

        public static final String COMPANY_NOT_FOUND="Company Not Found";

        public static final String COMPANY_ADMIN_NOT_FOUND="Company Admin Not Found";

        public static final String AMC_TYPE_NOT_FOUND="AMC Type Not Found";

        public static final String PAYMENT_NOT_FOUND="Payment Type Not Found";

        public static final String BUTTON_MODEL_NOT_FOUND="Button Model Not Found";

        public static final String MOTOR_TYPE_NOT_FOUND="Motor Type Not Found";

        public static final String DRIVER_TYPE_NOT_FOUND ="Driver Type Not Found";

        public static final String CONTROLLER_TYPE_NOT_FOUND="Controller Type Not Found";

        public static final String WORK_ORDER_NOT_FOUND="Work Order Not Found";

        public static final String SITE_NOT_FOUND="Site or Apartment Not Found";



    }

    public static final class ValidationMessages
    {
        public static final String NULL_OR_EMPTY_SUPER_ADMIN_EMAIL_ID ="Null or Empty Super Admin EmailId Value";
        public static final String NULL_OR_EMPTY_ADMIN_EMAIL_ID="Null or Empty Admin EmailId Value";
        public static final String NULL_OR_EMPTY_EMPLOYEE_EMAIL_ID="Null or Empty Employee EmailId Value";
        public static final String NULL_OR_EMPTY_EMPLOYEE_NAME="Null or Empty Employee Name Value";
        public static final String NULL_OR_EMPTY_EMPLOYEE_MOBILE_NUMBER="Null or Empty Employee Mobile Number Value";
        public static final String NULL_OR_EMPTY_ADMIN_NAME="Null or Empty Admin Name Value";
        public static final String NULL_OR_EMPTY_ADMIN_MOBILE_NUMBER="Null or Empty Admin Mobile Number Value";
        public static final String NULL_OR_EMPTY_COMPANY_ID="Null or Empty Company Id Value";
        public static final String nullOrEmptyAddress="Null or Empty Address Value";

        public static final String emptyEmailId="Null or Empty Email Id";

        public static final String emptyCompanyName="Null or Empty Company Name";

        public static final String emptyGstNumber="Null or Empty GST Number";


        public static final String CHECK_IN_EXISTS="Already Check In Exists";
        public static final String CHECK_IN_DOES_NOT_EXISTS="Check In Doesn't Exists";
        public static final String CHECK_OUT_EXISTS="Already Check Out Exists";



    }

    public static final class StatusCode{


        public static final long noContent=204;

        public static final long otpExpired=410;

        public static final long invalidOtp=422;

        public static final long loggedInOtherDevice =103;

        public static final long badRequest=400;

        public static final long unAuthorized=401;

        public static final long notFound=404;

        public static final long methodNotAllowed=405;

        public static final long success=200;

        public static final long error =500;

        public static final long userLoggedOut =601;


    }

    public static final class RequestStatus{

        public static final String pending = "PENDING";
        public static final String approved = "APPROVED";
        public static final String rejected = "REJECTED";
        public static final String Partial_Approval = "PARTIAL APPROVAL";
        public static final String Final_Approval = "FINAL APPROVAL";
        public static final String defaultCase = "DEFAULT";
        public static final String inProgress = "IN PROGRESS";
        public static final String cancelled = "CANCELLED";


    }

    public static final class NotificationMessages
    {

        public static final String NEW_TRAVEL_REQUEST = "New Travel Request";
        public static final String NEW_TRAVEL_REQUEST_BODY = "Has Submitted A Travel Request From start To end. Please Review.";
        public static final String UPDATE_TRAVEL_REQUEST = "Update Travel Request";
        public static final String UPDATE_TRAVEL_REQUEST_BODY = "Has Updated A Travel Request From start To end. Please Review.";

        public static final String APPROVE_TRAVEL_REQUEST = "Travel Request Is Approved";
        public static final String APPROVE_TRAVEL_REQUEST_BODY = "Your Travel Request has been Approved By des";
        public static final String PARTIAL_APPROVE_TRAVEL_REQUEST = "Travel Request Was Partially Approved";
        public static final String PARTIAL_APPROVE_TRAVEL_REQUEST_BODY = "Your Travel Request Has Been Partially Approved By des";
        public static final String REJECT_TRAVEL_REQUEST = "Travel Request Is Rejected";
        public static final String REJECT_TRAVEL_REQUEST_BODY = "Your Travel Request Has Been Rejected By des";
        public static final String NEW_EXPENSE_CLAIM_REQUEST = "New Expense Claim Request";
        public static final String NEW_EXPENSE_CLAIM_REQUEST_BODY = "Has Submitted Expense Claim For Trip: tripId";
        public static final String APPROVE_EXPENSE_CLAIM_REQUEST = "Expense Claim Request Was Approved ";
        public static final String APPROVE_EXPENSE_CLAIM_REQUEST_BODY = "Your Expense Claim Request Was Approved By des";
        public static final String REJECT_EXPENSE_CLAIM_REQUEST = "Expense Claim Request Was Rejected";
        public static final String REJECT_EXPENSE_CLAIM_REQUEST_BODY = "Your Expense Claim Request Was Rejected By des";
        public static final String PARTIAL_APPROVAL_EXPENSE_CLAIM = "Expense Claim Request Was Partially Approved";
        public static final String PARTIAL_APPROVAL_EXPENSE_CLAIM_BODY = "Your Expense Claim Request Was Partially Approved By des";
        public static final String BOOKMARK_TRAVEL_REQUEST="Travel Request Bookmarked Successfully.";
        public static final String UNDO_BOOKMARK_TRAVEL_REQUEST="Bookmark Removed Successfully";

        public static final String BOOKMARK_EXPENSE_REQUEST="Expense Request Bookmarked Successfully.";
        public static final String UNDO_BOOKMARK_EXPENSE_REQUEST="Bookmark Removed Successfully";

        public static final String EMPTY_FCM_TOKEN="Empty FCM Token";

        public static final String CANCEL_TRAVEL_REQUEST="Travel request cancelled successfully";
        public static final String CANCELLED_TRAVEL_REQUEST_BODY="Cancelled Travel Request";
        public static final String CANCELLED_TRAVEL_REQUEST="Cancelled Travel Request";
        public static final String RESUBMIT_TRAVEL_REQUEST="Travel request resubmitted successfully";
        public static final String SUBMIT_TRIP_EXTENSION_REQUEST="Trip Extension Request Submitted successfully";


        public static final String CO_NEW_TRAVEL_REQUEST = "New CoTraveller Travel Request";
        public static final String CO_NEW_TRAVEL_REQUEST_BODY = "Has Included You In A Travel Request From start To end";
        public static final String CO_UPDATE_TRAVEL_REQUEST = "Update Travel Request";
        public static final String CO_UPDATE_TRAVEL_REQUEST_BODY = "Has Updated A Travel Request From start To end";

        public static final String APPROVED_TRIP_EXTENSION_REQUEST="Trip Extension Request Is Approved";
        public static final String APPROVED_TRIP_EXTENSION_REQUEST_BODY = "Your Trip Extension Request Has Been Approved By des";
        public static final String REJECTED_TRIP_EXTENSION_REQUEST="Trip Extension Request Is Rejected";
        public static final String REJECTED_TRIP_EXTENSION_REQUEST_BODY = "Your Trip Extension Request Has Been Rejected By des";
        public static final String TRIP_EXTENSION_REQUEST="Trip Extension Request";

        public static final String TRIP_EXTENSION_REQUEST_BODY = "Has Submitted A Trip Extension Request. Please Review.";
        public static final String APPROVED_FINANCE_REQUEST = "Finance Request is Approved";
        public static final String APPROVED_FINANCE_REQUEST_BODY = "Your expense claim for Trip ID : {tripId} has been approved by Finance.";
        public static final String REJECTED_FINANCE_REQUEST = "Finance Request is Rejected";
        public static final String REJECTED_FINANCE_REQUEST_BODY = "Your expense claim for Trip ID : {tripId} has been rejected.";


        public static final String CONFORMED_FLIGHT_BOOKING="Flight Booked Successfully";
        public static final String CONFORMED_FLIGHT_BOOKING_BODY="Your flight has been confirmed.";
        public static final String CANCELLED_FLIGHT_BOOKING="Flight Booking Cancelled";
        public static final String CANCELLED_FLIGHT_BOOKING_BODY="Your flight booking has been cancelled.";


        public static final String CONFORMED_HOTEL_BOOKING="Hotel Booking Confirmed";
        public static final String CONFORMED_HOTEL_BOOKING_BODY="Your hotel reservation is confirmed.";
        public static final String CANCELLED_HOTEL_BOOKING="Hotel Booking Cancelled";
        public static final String CANCELLED_HOTEL_BOOKING_BODY="Your hotel reservation has been cancelled.";
    }

    public static final class NotificationType{

        public static final long travelRequest=1L;

        public static final long expenseClaimRequest=2L;

        public static final long approveOrRejectTravelRequest=3L;

        public static final long approveOrRejectExpenseClaimRequest=4L;

        public static final long reviewFinanceRequests=5L;

    }

    public static final class Values{

        public static final String wtdEmployeeId="110200091";

        public static final String officeTour="Official Tour";

        public static final String deputation="Deputation";

        public static final String transfer="Transfer";

        public static final String notEligibleFor4Wheeler="Not Eligible For 4 Wheeler";

        public static final Long coTravellerSave=1L;

        public static final Long coTravellerUpdate=2L;

        public static final String financeEmployeeId="110100304";

        public static final String notificationSent="SENT";
        public static final int mmtNotificationType1=1;
        public static final int mmtNotificationType2=2;

    }

    public static final class ExpenseValues
    {
        public static final String withATP="WITH ATP";

        public static final String withOutATP="WITHOUT ATP";

        public static final String saveExpenseRequest="SAVE";

        public static final String submitExpenseRequest="SUBMIT";

        public static final String manualExpenseSource="MANUAL";

        public static final String saathiOrKvExpenseSource="SaathiAndKV";

        public static final String MMTExpenseSource="MMT";

        public static final String employeeAsPaymentSource="EMPLOYEE";

        public static final String mmtWalletAsPaymentSource="MMT WALLET";

        public static final Integer saveOrUpdateImgFlag=1;

        public static final Integer inActiveImgFlag=2;

        public static final String EXPENSE_CLAIM_REQUEST_SUBMIT="Expense Claim Request submitted successfully";

        public static final String EXPENSE_CLAIM_REQUEST_SAVE="Expense Claim Request Saved";

        public static final String ACTUAL_ELIGIBILITY="Actuals";

        public static final double localTotalKilometersLimit =150.0;
    }

    public static final class DBValues{

        public static final String DB_SCHEMA="nsl_nuziyatra";
        public static final String N_TRAVEL_REQUEST="nzy_n_travel_request";
        public static final String TRAVEL_REQUEST_APPROVAL_HISTORY="nzy_travel_request_approval_history";
        public static final String TRAVEL_GROUP_REQUEST="nzy_travel_request_group";
    }
    
    
    public static final class DarwinBoxApiType {
        public static final String fetchActiveEmployee = "ACTIVE EMPLOYEE";
        public static final String fetchInActiveEmployee = "INACTIVE EMPLOYEE";
        public static final String fetchDesignation = "DESIGNATION";
        public static final String fetchDepartment = "DEPARTMENT";
        public static final String active = "Active";
        public static final String inActive = "Inactive";


    }

    public static final class ColorCodes
    {
        public static final String approvedColor="rgba(0, 135, 63, 1)";
        public static final String pendingColor="rgba(228, 149, 66, 1)";
        public static final String rejectColor="rgba(255, 0, 0, 1)";

    }


    public static final class MMTValues {
        public static final String booked = "BOOKING";
        public static final String cancelled = "CANCELLATION";
        public static final String recalled = "recalled";

    }

    public static final class SaathiAndKv
    {
        public static final String saathi = "SAATHI";
        public static final String kisanVikas = "KISAN VIKAS";
        public static final String notASaathiUser = "NOT A SAATHI USER";
        public static final String notAKvUser = "NOT A KISAN VIKAS USER";
        public static final String NA = "NA";
        public static final String TwoWheeler = "2 Wheeler";
        public static final String FourWheeler = "4 Wheeler";
        public static final int hourCondition=25;
        public static final int kmCondition=15;


    }

    public static final class DeviationMessages{
        public static final String LIMIT_EXCEEDED="Claimed Amount: cli exceeds Eligible Amount : eli";
        public static final String NOT_ELIGIBLE="Not Eligible for : eli";
    }

    public static final class DeviationTypes{
        public static final Integer FLIGHT_DEVIATION=1;
    }


    public static final class OwnVehiclePolicyDepartments
    {
        public static final String SALES_DEPARTMENT="Sales";
        public static final String PRODUCTION_DEPARTMENT="Production";
        public static final String FIELD_QUALITY_DEPARTMENT="Field Quality";
    }

    public static final class OwnVehiclePolicyDesignations
    {
        //Sales
        public static final String REGIONAL_MANAGER="Regional Manager";
        public static final String REGIONAL_SALES_MANAGER ="Regional Sales Manager";
        public static final String TERRITORY_SALES_LEAD ="Territory Sales Lead";
        public static final String SALES_OFFICER="Sales Officer";
        public static final String ZONAL_MANAGER="Zonal Manager";
        public static final String ZONAL_SALES_MANAGER="Zonal Sales Manager";


        // Production
        public static final String PRODUCTION_CENTER_HEAD="Production Centre Head";
        public static final String TERRITORY_PRODUCTION_HEAD="Territory Production Lead";
        public static final String PRODUCTION_OFFICER="Production Officer";


        //Field Quality
        public static final String FIELD_QUALITY_MANAGER="Field Quality Manager";
        public static final String FIELD_QUALITY_LEAD="Field Quality Lead";
        public static final String FIELD_QUALITY_OFFICER="Field Quality Officer";

    }
}

