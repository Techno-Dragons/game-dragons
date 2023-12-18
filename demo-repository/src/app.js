import toastr from 'toastr';

toastr.options = {
  closeButton: true,
  debug: false,
  newestOnTop: true,
  progressBar: true,
  positionClass: "toast-top-right",
  preventDuplicates: false,
  onclick: null,
  showDuration: "300",
  hideDuration: "1000",
  timeOut: "5000",
  extendedTimeOut: "1000",
  showEasing: "swing",
  hideEasing: "linear",
  showMethod: "fadeIn",
  hideMethod: "fadeOut"
};

function parseMsg(msg) {
  return msg.split(";ttl=");
}

export function toastWarning(msg) {
  const [_msg, ttl] = parseMsg(msg);
  if (ttl && parseInt(ttl) < new Date().getTime()) return;

  toastr["warning"](_msg, "경고");
}

export function toastNotice(msg) {
  const [_msg, ttl] = parseMsg(msg);
  if (ttl && parseInt(ttl) < new Date().getTime()) return;

  toastr["success"](_msg, "알림");
}