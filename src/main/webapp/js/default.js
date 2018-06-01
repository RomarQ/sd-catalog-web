// Shows Category Modal that allows to Add/Update a category
function openCategoryEditor(data) {
    if ( data.status === 'success' )
        $('#categoryModal').modal();
}

// Closes Category Modal after deleting it
function closeCategoryEditor(data) {
    $('#categoryModal').modal("hide");
}

// Shows Product Modal that allows to Add/Update a product
function openProductEditor(data) {
    if ( data.status === 'success' )
        $('#productModal').modal();
}

// Closes Product Modal after deleting it
function closeProductEditor(data) {
    $('#productModal').modal("hide");
}