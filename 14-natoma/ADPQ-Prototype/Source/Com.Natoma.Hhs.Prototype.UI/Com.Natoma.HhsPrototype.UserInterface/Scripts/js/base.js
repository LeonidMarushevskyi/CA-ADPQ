// Function to take a 'date' string value, and return a 'short date' string (M/d/yyyy)
function toShortDateString(dateString) {
    if (!dateString) {
        return '';
    }

    var date = new Date(dateString);
    return date.getMonth() + 1 + '/' + date.getDate() + '/' + date.getFullYear();
}