<%@page import="com.tdil.milka.web.MeltButton"%>
<%@page import="com.tdil.milka.web.MilkaPhotoUtils"%>
<%@page import="com.tdil.milka.model.valueobjects.MilkaPhotoValueObject"%>
<%@page import="java.util.List"%>
<% List<MilkaPhotoValueObject> milkaPhotos = MilkaPhotoUtils.getPhotos(); %>
<% List<MilkaPhotoValueObject> toDisplay = MilkaPhotoUtils.getDisplayPart(milkaPhotos); %>
<style>
<!--
.fotoHelper {
	background-image: url(../images/foto2.jpg);
	background-repeat: no-repeat;
	background-position: center center;
}

-->
</style>
<div id="fotos">
	<h2>TU FOTO MILKA</h2>
		<ul>
			<% 	int milkaPhotoIndex = 0;
				for (MilkaPhotoValueObject mfvo : toDisplay) { %>
					<% if (milkaPhotoIndex == 0) { %>
						<li class="f-grande"><div class="fotoHelper" style="width:212px; height:157px; background-image:url(./downloadThumb.st?id=<%=mfvo.getIdApprovedData()%>&width=157&height=157&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>);"><a href="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" title="<%=mfvo.getImageTitle()%>" id="lk-<%=mfvo.getIdClickCounter()%>" rel="lightbox[pm]" button="<%=mfvo.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mfvo.getIdClickCounter())%>" class="activo"><span class="fotoHelper" style="width:212px; height:157px; background-image:url(./downloadThumb.st?id=<%=mfvo.getIdApprovedData()%>&amp;width=157&amp;height=157&amp;type=PUBLIC&amp;ext=<%=mfvo.getExtApprovedData()%>);"><a href="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>"  id="lk-<%=mfvo.getIdClickCounter()%>2" rel="lightbox[pm]" button="<%=mfvo.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mfvo.getIdClickCounter())%>" class="activo"><img src="images/null.gif" width="212" height="157"/></a></span></a></div><!-- title="< % = mfvo.getImageTitle() % >" -->
						</li>
					<% } else { %>
						<li class="f-grande"><div class="fotoHelper" style="width:66px; height:66px; background-image:url(./downloadThumb.st?id=<%=mfvo.getIdApprovedData()%>&width=66&height=66&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>)"><a href="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" id="lk-<%=mfvo.getIdClickCounter()%>" rel="lightbox[pm]" button="<%=mfvo.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mfvo.getIdClickCounter())%>" class="activo"><img src="images/null.gif" width="66" height="66" /></a></div></li><!--  title="< % = m f vo.getImageTitle() % >" -->
					<% } %>
			<% 	milkaPhotoIndex = milkaPhotoIndex + 1;
				} %>
		</ul>
	</div>
	<!-- end fotos-->
</div>
<% List<MilkaPhotoValueObject> hiddenDisplay = MilkaPhotoUtils.getHiddenPart(milkaPhotos); %>
<div class="hide">
	<% for (MilkaPhotoValueObject mfvo : hiddenDisplay) { %>
		<a href="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" id="lk-<%=mfvo.getIdClickCounter()%>" rel="lightbox[pm]" title="<%=mfvo.getImageTitle()%>" button="<%=mfvo.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mfvo.getIdClickCounter())%>" class="activo">a</a>
	<% } %>
</div>