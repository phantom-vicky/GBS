!(function () {
    "use strict";
    function t(t, l) {
        return {
            link: function (t, e, l, i) {
                t.duallistOption.leftContainerScrollEnd &&
                    $(e)
                        .find(".left-sub-container")
                        .bind("scroll", function () {
                            $(this)[0].scrollHeight - $(this).scrollTop() === $(this).height() && t.$evalAsync(t.duallistOption.leftContainerScrollEnd);
                        });
                t.duallistOption.rightContainerScrollEnd &&
                    $(e)
                        .find(".right-sub-container")
                        .bind("scroll", function () {
                            $(this)[0].scrollHeight - $(this).scrollTop() === $(this).height() && t.$evalAsync(t.duallistOption.rightContainerScrollEnd);
                        });
                t.duallistOption.leftContainerSearch &&
                    $(e)
                        .find("#leftsearch")
                        .bind("change paste keyup", function () {
                            var e = $(this).val();
                            t.$evalAsync(t.duallistOption.leftContainerSearch(e));
                        });
                t.duallistOption.rightContainerSearch &&
                    $(e)
                        .find("#rightsearch")
                        .bind("change paste keyup", function () {
                            var e = $(this).val();
                            t.$evalAsync(t.duallistOption.rightContainerSearch(e));
                        });
                t.duallistOption.onMoveRight &&
                    $(e)
                        .find("#moveRightButton")
                        .bind("click", function () {
                            t.$evalAsync(t.duallistOption.onMoveRight);
                        });
                t.duallistOption.onMoveLeft &&
                    $(e)
                        .find("#moveLeftButton")
                        .bind("click", function () {
                            t.$evalAsync(t.duallistOption.onMoveLeft);
                        });
            },
            restrict: "E",
            scope: { leftscope: "=", rightscope: "=", duallistOption: "=", leftsearch: "=", customData: "=" },
            bindToController: !1,
            controller: e,
            controllerAs: "vm",
            templateUrl: function (t, e) {
                return e.templateUrl || "dual/duallist.html";
            },
            replace: !0,
        };
    }
    function e(t) {
        (t.moveRight = function () {
            for (var e = 0; e < t.leftscope.length; e++)
                if (t.leftscope[e].selected) {
                    (t.leftscope[e].selected = !1), t.rightscope.push(t.leftscope[e]);
                    var l = t.leftscope.indexOf(t.leftscope[e]);
                    t.leftscope.splice(l, 1), e--;
                }
            t.leftSelectAll = !1;
        }),
            (t.moveLeft = function () {
                for (var e = 0; e < t.rightscope.length; e++)
                    if (t.rightscope[e].selected) {
                        (t.rightscope[e].selected = !1), t.leftscope.push(t.rightscope[e]);
                        var l = t.rightscope.indexOf(t.rightscope[e]);
                        t.rightscope.splice(l, 1), e--;
                    }
                t.rightSelectAll = !1;
            }),
            (t.selectAllLeftContainer = function () {
//                angular.forEach(t.leftscope, function (e) {
//                    t.leftSelectAll ? (e.selected = !0) : (e.selected = !1);
//                });

//angular.forEach(t.leftscope.filter(item => item.name.toLowerCase().includes(t.leftSearch.toLowerCase())), function (e) {
//        e.selected = t.leftSelectAll;
//    });

angular.forEach(t.leftscope, function (e) {
        if (!t.leftSearch || e.name.toLowerCase().includes(t.leftSearch.toLowerCase())) {
            e.selected = t.leftSelectAll;
        }
    });

            }),
            (t.selectAllRightContainer = function () {
//                angular.forEach(t.rightscope, function (e) {
//                    t.rightSelectAll ? (e.selected = !0) : (e.selected = !1);
//                });
//angular.forEach(t.rightscope.filter(item => item.name.toLowerCase().includes(t.rightSearch.toLowerCase())), function (e) {
//            e.selected = t.rightSelectAll;
//        });
angular.forEach(t.rightscope, function (e) {
        if (!t.rightSearch || e.name.toLowerCase().includes(t.rightSearch.toLowerCase())) {
            e.selected = t.rightSelectAll;
        }
    });

            });
    }
    function l(t) {
        t.put(
            "dual/duallist.html",
            '<div class="row ngduallist"> <div class="col-sm-4 left-container"> <label>{{duallistOption.leftContainerLabel}}</label> <span class="info-container"> <span class="info" ng-if="leftscope.length > 0">Showing all{{leftscope.length}}</span> <span class="info" ng-if="leftscope.length==0">Empty List</span> </span> <form class="form-inline"> <div> <div class="input-group"> <input type="text" class="form-control" id="leftsearch" placeholder="Search"> <div class="input-group-addon"> <input title="toggle all" class="all pull-right" ng-model="leftSelectAll" ng-change="selectAllLeftContainer()" type="checkbox"> </div></div></div></form> <div class="left-sub-container"> <div class="list-group" id="list1"> <a ng-class="{"active":data.selected}" ng-repeat="data in leftscope track by $index" href="#" class="list-group-item">{{data.name}}<input class="pull-right" ng-model="data.selected" type="checkbox"></a> </div></div></div><div class="col-md-2 v-center"> <button id="moveRightButton" ng-click="moveRight()" title="Send to list 2" class="btn btn-default center-block add"><i class="glyphicon glyphicon-chevron-right"></i></button> <button id="moveLeftButton" ng-click="moveLeft()" title="Send to list 1" class="btn btn-default center-block remove"><i class="glyphicon glyphicon-chevron-left"></i></button> </div><div class="col-sm-4 right-container"> <label>{{duallistOption.rightContainerLabel}}</label> <span class="info-container"> <span class="info" ng-if="rightscope.length > 0">Showing all{{rightscope.length}}</span> <span class="info" ng-if="rightscope.length==0">Empty List</span> </span> <form class="form-inline"> <div> <div class="input-group"> <input type="text" class="form-control" id="rightsearch" placeholder="Search"> <div class="input-group-addon"> <input ng-model="rightSelectAll" ng-change="selectAllRightContainer()" title="toggle all" class="all pull-right" type="checkbox"> </div></div></div></form> <div class="right-sub-container"> <div class="list-group" id="list2"> <a ng-class="{"active":data.selected}" ng-repeat="data in rightscope track by $index" href="#" class="list-group-item">{{data.name}}<input ng-model="data.selected" class="pull-right" type="checkbox"></a> </div></div></div></div>'
        );
    }
    angular.module("ng-duallist", []).directive("duallist", t).run(l), (t.$inject = ["$rootScope", "$filter"]), (l.$inject = ["$templateCache"]), (e.$inject = ["$scope"]);
})();
