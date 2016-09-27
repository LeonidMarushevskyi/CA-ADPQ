/* Run on load */
ready = function() {
  $('#facilities_table').DataTable( {
      columnDefs: [ 
        {
          "targets": 0,
          "orderable": false
        },
        {
            targets: [ 1 ],
            orderData: [ 1, 0 ]
        }, 
        {
            targets: [ 2 ],
            orderData: [ 2, 0 ]
        }, 
        {
            targets: [ 3 ],
            orderData: [ 3, 0 ]
        },
        {
            targets: [ 12 ],
            orderData: [ 12, 0, 1 ]
        }

      ]
  }); // data table


};

// Turbo link has its own event
$(document).ready(ready)
$(document).on('page:load', ready)