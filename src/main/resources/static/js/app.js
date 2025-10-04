var api = apimock;

var app = (function () {
    var author = "";
    var blueprints = [];

    var setAuthor = function (newAuthor) {
        author = newAuthor;
        $("#selectedAuthor").text(author);
    };

    var updateBlueprints = function (authname) {
        api.getBlueprintsByAuthor(authname, function (data) {
            blueprints = data.map(function (bp) {
                return {name: bp.name, points: bp.points.length};
            });

            $("#blueprintsTable tbody").empty();

            blueprints.map(function (bp) {
                $("#blueprintsTable tbody").append(
                    `<tr>
                        <td>${bp.name}</td>
                        <td>${bp.points}</td>
                        <td><button class="btn btn-info" onclick="app.drawBlueprint('${authname}', '${bp.name}')">Open</button></td>
                    </tr>`
                );
            });

            var totalPoints = blueprints.reduce(function (sum, bp) {
                return sum + bp.points;
            }, 0);

            $("#totalPoints").text(totalPoints);
        });
    };

    var drawBlueprint = function (author, blueprintName) {
        api.getBlueprintsByNameAndAuthor(author, blueprintName, function (blueprint) {

            $("#currentBlueprintName").text(blueprint.name);

            var canvas = document.getElementById("blueprintCanvas");
            var ctx = canvas.getContext("2d");

            ctx.clearRect(0, 0, canvas.width, canvas.height);

            if (blueprint.points.length > 0) {
                ctx.beginPath();
                ctx.moveTo(blueprint.points[0].x, blueprint.points[0].y);
                for (let i = 1; i < blueprint.points.length; i++) {
                    ctx.lineTo(blueprint.points[i].x, blueprint.points[i].y);
                }
                ctx.stroke();
            }
        });
    };

    return {
        setAuthor: setAuthor,
        updateBlueprints: updateBlueprints,
        drawBlueprint: drawBlueprint
    };

})();
