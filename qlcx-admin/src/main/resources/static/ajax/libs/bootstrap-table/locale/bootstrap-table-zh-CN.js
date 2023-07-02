(function ($) {
    'use strict';

    $.fn.bootstrapTable.locales['zh-CN'] = {
        formatLoadingMessage: function () {
            return 'Đang tải dữ liệu, vui lòng đợi ...';
        },
        formatRecordsPerPage: function (pageNumber) {
            return pageNumber + ' ';
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return 'Đầu tiên ' + pageFrom + ' Đến ' + pageTo + ' Của  ' + totalRows + ' Hồ sơ.';
        },
        formatSearch: function () {
            return 'Tìm kiếm';
        },
        formatNoMatches: function () {
            return 'Không tìm thấy kết quả';
        },
        formatPaginationSwitch: function () {
            return 'Ẩn / hiện phân trang';
        },
        formatRefresh: function () {
            return 'Làm mới';
        },
        formatToggle: function () {
            return 'Chuyển hiển thị';
        },
        formatColumns: function () {
            return 'Cột';
        },
        formatExport: function () {
            return 'Xuất file excel';
        },
        formatClearFilters: function () {
            return 'Bộ lọc trống';
        }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

})(jQuery);
