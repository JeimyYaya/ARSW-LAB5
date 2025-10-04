var apiclient = (function () {

    return {
        getBlueprintsByAuthor: function(author, callback) {
            $.get("/blueprints/" + author, function(data) {
                callback(data);
            });
        },

        getBlueprintsByNameAndAuthor: function(author, name, callback) {
            $.get("/blueprints/" + author + "/" + name, function(data) {
                callback(data);
            });
        }
    };

})();
