@echo off
:: 关闭命令回显，@echo off 开头标配
chcp 65001 >nul
:: 设置编码UTF-8，防止中文乱码

echo ====================== 开始一键上传 ======================
git add .
git commit -m %0
git push origin main
echo 成功
pause