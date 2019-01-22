/*
 * Copyright 2019 Patrick Maher<dev@gnoxy.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gnoxy.SoftLi.repository;

import com.gnoxy.SoftLi.am.DBLicenseRight;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@Repository("LicenseRightRepository")
public interface LicenseRightRepository extends JpaRepository<DBLicenseRight, String> {
    
//    @Query("select l from LicenseRight l")
//    Collection<DBLicenseRight> findAllLicenseRights();
//    
//    @Query("select l from LicenseRight l where l.appID = ?1")
//    Collection<DBLicenseRight> findLicenseRightsByAppId(String appId);
    
//    @Query("select l from LicenseRight l where l.id = ?1")
//    DBLicenseRight findLicenseRightById(String id);
    
//    @Modifying
//    @Query("update LicenseRight l set l.qtyOwned = :qty where id = :id")
//    int updateLicenseRightQtyOwned(@Param("id") String id, @Param("qty") int quantity);
//    
//    @Modifying
//    @Query("update LicenseRight l set l.qtyReserved = :qty where id = :id")
//    int updateLicenseRightQtyReserved(@Param("id") String id, @Param("qty") int quantity);
    
    
}
